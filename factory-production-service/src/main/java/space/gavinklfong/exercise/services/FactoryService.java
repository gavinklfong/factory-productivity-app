package space.gavinklfong.exercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gavinklfong.exercise.dtos.FactoryDailyProduction;
import space.gavinklfong.exercise.exceptions.RecordNotFoundException;
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;
import space.gavinklfong.exercise.repositories.FactoryRepository;
import space.gavinklfong.exercise.repositories.HourlyProductionRepository;
import space.gavinklfong.exercise.repositories.dtos.DailyProduction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.zone.ZoneOffsetTransition;
import java.util.UUID;

@Service
public class FactoryService {

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private HourlyProductionRepository hourlyProductionRepository;

    public Flux<Factory> retrieveEnergyFarmsByType(FactoryType type) {
        return factoryRepository.findByTypeOrderByNameAsc(type)
                .switchIfEmpty(Mono.error(new RecordNotFoundException()));
    }

    /**
     * This method retrieves daily production by a specified factory id and a date range.
     *
     * Here is the process flow:
     * 1. Retrieve factory record by id
     * 2. Obtain timezone from the factory record and pass to repository together with farm id
     *    and date range for hourly production retrieval
     * 3. For each daily record, calculate capacity factor and determine if some hour data is missing
     *
     * The following logic determine whether some data is missing:
     *
     * Does day-light saving transition occur with a gap?
     * For example, clock move forward from 01:00 to 02:00. The total record count should be 23,
     * otherwise, some data is missing.
     *
     * Does day-light saving transition occur with an overlap?
     * For example, clock move backward from 02:00 to 01:00. The total record count should be 25,
     * otherwise, some data is missing.
     *
     * If no day-light saving transition occurs, then a daily record should have 24 hourly records,
     * Otherwise, some data is missing.
     *
     *
     * @param energyFarmId
     * @param startDate
     * @param endDate
     * @return
     */
    public Flux<FactoryDailyProduction> retrieveDailyProduction(UUID energyFarmId, LocalDate startDate, LocalDate endDate) {
        return factoryRepository.findById(energyFarmId)
                .switchIfEmpty(Mono.error(new RecordNotFoundException()))
                .flatMapMany(energyFarm ->
                    hourlyProductionRepository.aggregateDailyProduction(energyFarm.getId(), startDate, endDate, energyFarm.getTimezone())
                    .switchIfEmpty(Mono.error(new RecordNotFoundException()))
                    .map(item -> constructProductionDto(energyFarm.getCapacity(), item, energyFarm.getTimezone()))
        );
    }

    private FactoryDailyProduction constructProductionDto(Integer capacity, DailyProduction production, ZoneId timezone) {

        Double capacityFactor = production.getProduction().doubleValue() / (capacity * production.getCount());

        return FactoryDailyProduction.builder()
                .date(production.getDate())
                .capacityFactor(capacityFactor)
                .production(production.getProduction())
                .partial(isSomeHourDataMissing(production.getDate(), timezone, production.getCount()))
                .build();
    }

    private boolean isSomeHourDataMissing(LocalDate localDate, ZoneId timezone, int recordCount) {
        ZoneOffsetTransition transition = timezone.getRules().nextTransition(localDate.atStartOfDay(timezone).toInstant());
        LocalDate nextTransactionLocalDate = transition.getInstant().atZone(timezone).toLocalDate();

        if (localDate.isEqual(nextTransactionLocalDate)) {
            if ((transition.isGap() && recordCount < 23) ||
            (transition.isOverlap() && recordCount < 25))
                return true;
            else
                return false;
        } else {
            return (recordCount < 24);
        }
    }

}
