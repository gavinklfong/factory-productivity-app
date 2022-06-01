package space.gavinklfong.exercise.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import space.gavinklfong.exercise.models.HourlyProduction;
import space.gavinklfong.exercise.repositories.dtos.DailyProduction;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

public interface HourlyProductionRepository extends ReactiveCrudRepository<HourlyProduction, Integer> {

    @Query("SELECT DATE_TRUNC('day', start_hour at time zone :timezone) AS date," +
            "COUNT(id) AS count," +
            "sum(production) as production " +
            "FROM hourly_production " +
            "WHERE factory_id = :factoryId " +
            "AND DATE_TRUNC('day', start_hour at time zone :timezone) >= :startDate::date " +
            "AND DATE_TRUNC('day', start_hour at time zone :timezone) <= :endDate::date " +
            "GROUP BY DATE_TRUNC('day', start_hour at time zone :timezone) " +
            "ORDER BY date ASC")
    Flux<DailyProduction> aggregateDailyProduction(UUID factoryId, LocalDate startDate, LocalDate endDate, ZoneId timezone);

}
