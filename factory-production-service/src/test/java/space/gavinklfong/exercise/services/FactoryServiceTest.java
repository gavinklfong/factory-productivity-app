package space.gavinklfong.exercise.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import space.gavinklfong.exercise.dtos.FactoryDailyProduction;
import space.gavinklfong.exercise.exceptions.RecordNotFoundException;
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;
import space.gavinklfong.exercise.repositories.FactoryRepository;
import space.gavinklfong.exercise.repositories.HourlyProductionRepository;
import space.gavinklfong.exercise.repositories.dtos.DailyProduction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static space.gavinklfong.exercise.services.FactoryServiceTest.TestConstants.*;

@SpringJUnitConfig(FactoryService.class)
public class FactoryServiceTest {

    @MockBean
    private FactoryRepository factoryRepository;

    @MockBean
    private HourlyProductionRepository hourlyProductionRepository;

    @Autowired
    private FactoryService factoryService;

    @BeforeEach
    void setup() {
        when(factoryRepository.findById(FOOD_FACTORY_ID)).thenReturn(Mono.just(FOOD_FACTORY));
    }

    @Test
    void givenRecordAvailable_whenFindByType_thenReturnResult() {
        when(factoryRepository.findByTypeOrderByNameAsc(FactoryType.FOOD_FACTORY)).thenReturn(Flux.just(FOOD_FACTORY));

        Flux<Factory> energyFarmFlux = factoryService.retrieveEnergyFarmsByType(FactoryType.FOOD_FACTORY);

        StepVerifier
                .create(energyFarmFlux)
                .expectNext(FOOD_FACTORY)
                .verifyComplete();

        verify(factoryRepository).findByTypeOrderByNameAsc(FactoryType.FOOD_FACTORY);
    }

    @Test
    void givenNoRecordFound_whenFindByType_thenThrowException() {
        when(factoryRepository.findByTypeOrderByNameAsc(FactoryType.FOOD_FACTORY)).thenReturn(Flux.empty());

        Flux<Factory> energyFarmFlux = factoryService.retrieveEnergyFarmsByType(FactoryType.FOOD_FACTORY);

        StepVerifier
                .create(energyFarmFlux)
                .expectError(RecordNotFoundException.class)
                .verify();

        verify(factoryRepository).findByTypeOrderByNameAsc(FactoryType.FOOD_FACTORY);
    }

    @Test
    void givenValidRequest_whenRetrieveDailyProduction_thenReturnResult() {

        LocalDate startDate = LocalDate.of(2022, 1, 15);
        LocalDate endDate = LocalDate.of(2022, 1, 16);
        DailyProduction dailyProduction1 = new DailyProduction(startDate, 1000, 24);
        DailyProduction dailyProduction2 = new DailyProduction(endDate, 450, 20);
        when(hourlyProductionRepository.aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE))
                .thenReturn(Flux.just(dailyProduction1, dailyProduction2));

        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(FOOD_FACTORY_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectNext(convertToDto(FOOD_FACTORY, dailyProduction1, false))
                .expectNext(convertToDto(FOOD_FACTORY, dailyProduction2, true))
                .verifyComplete();

        verify(factoryRepository).findById(FOOD_FACTORY_ID);
        verify(hourlyProductionRepository).aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE);
    }

    @Test
    void givenUnknownFarmId_whenRetrieveDailyProduction_thenThrowException() {

        when(factoryRepository.findById(UNKNOWN_ID)).thenReturn(Mono.empty());

        LocalDate startDate = LocalDate.of(2022, 1, 15);
        LocalDate endDate = LocalDate.of(2022, 1, 16);
        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(UNKNOWN_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectError(RecordNotFoundException.class)
                .verify();

        verify(factoryRepository).findById(UNKNOWN_ID);
        verify(hourlyProductionRepository, never()).aggregateDailyProduction(any(), any(), any(), any());
    }

    @DisplayName("Given day-light saving with a gap and hourly record count is 23, then return daily production with partial flag indicates no data missing")
    @Test
    void givenDateRangeWithDSTGapTransition_whenRetrieveDailyProduction_thenReturnResult() {

        LocalDate startDate = LocalDate.of(2021, 3, 14);
        LocalDate endDate = LocalDate.of(2021, 3, 14);
        DailyProduction dailyProduction = new DailyProduction(startDate, 1000, 23);
        when(hourlyProductionRepository.aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE))
                .thenReturn(Flux.just(dailyProduction));

        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(FOOD_FACTORY_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectNext(convertToDto(FOOD_FACTORY, dailyProduction, false))
                .verifyComplete();

        verify(factoryRepository).findById(FOOD_FACTORY_ID);
        verify(hourlyProductionRepository).aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE);
    }

    @DisplayName("Given day-light saving with a gap and hourly record count is 22, then return daily production with partial flag indicates some data missing")
    @Test
    void givenDateRangeWithDSTGapTransitionWithDataMissing_whenRetrieveDailyProduction_thenReturnResult() {

        LocalDate startDate = LocalDate.of(2021, 3, 14);
        LocalDate endDate = LocalDate.of(2021, 3, 14);
        DailyProduction dailyProduction = new DailyProduction(startDate, 1000, 22);
        when(hourlyProductionRepository.aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE))
                .thenReturn(Flux.just(dailyProduction));

        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(FOOD_FACTORY_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectNext(convertToDto(FOOD_FACTORY, dailyProduction, true))
                .verifyComplete();

        verify(factoryRepository).findById(FOOD_FACTORY_ID);
        verify(hourlyProductionRepository).aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE);
    }

    @DisplayName("Given day-light saving with a overlap and hourly record count is 25, then return daily production with partial flag indicates no data missing")
    @Test
    void givenDateRangeWithDSTOverlapTransition_whenRetrieveDailyProduction_thenReturnResult() {

        LocalDate startDate = LocalDate.of(2021, 11, 7);
        LocalDate endDate = LocalDate.of(2021, 11, 7);
        DailyProduction dailyProduction = new DailyProduction(startDate, 1000, 25);
        when(hourlyProductionRepository.aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE))
                .thenReturn(Flux.just(dailyProduction));

        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(FOOD_FACTORY_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectNext(convertToDto(FOOD_FACTORY, dailyProduction, false))
                .verifyComplete();

        verify(factoryRepository).findById(FOOD_FACTORY_ID);
        verify(hourlyProductionRepository).aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE);
    }

    @DisplayName("Given day-light saving with a overlap and hourly record count is 24, then return daily production with partial flag indicates some data missing")
    @Test
    void givenDateRangeWithDSTOverlapTransitionWithDataMissing_whenRetrieveDailyProduction_thenReturnResult() {

        LocalDate startDate = LocalDate.of(2021, 11, 7);
        LocalDate endDate = LocalDate.of(2021, 11, 7);
        DailyProduction dailyProduction = new DailyProduction(startDate, 1000, 24);
        when(hourlyProductionRepository.aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE))
                .thenReturn(Flux.just(dailyProduction));

        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(FOOD_FACTORY_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectNext(convertToDto(FOOD_FACTORY, dailyProduction, true))
                .verifyComplete();

        verify(factoryRepository).findById(FOOD_FACTORY_ID);
        verify(hourlyProductionRepository).aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE);
    }

    @Test
    void givenDateRangeWithRecordNotFound_whenRetrieveDailyProduction_thenThrowException() {

        LocalDate startDate = LocalDate.of(2021, 11, 7);
        LocalDate endDate = LocalDate.of(2021, 11, 7);
        when(hourlyProductionRepository.aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE))
                .thenReturn(Flux.empty());

        Flux<FactoryDailyProduction> result = factoryService.retrieveDailyProduction(FOOD_FACTORY_ID, startDate, endDate);

        StepVerifier
                .create(result)
                .expectError(RecordNotFoundException.class)
                .verify();

        verify(factoryRepository).findById(FOOD_FACTORY_ID);
        verify(hourlyProductionRepository).aggregateDailyProduction(FOOD_FACTORY_ID, startDate, endDate, TIME_ZONE);
    }

    private FactoryDailyProduction convertToDto(Factory factory, DailyProduction dailyProduction, boolean isPartial) {
        return FactoryDailyProduction.builder()
                .production(dailyProduction.getProduction())
                .date(dailyProduction.getDate())
                .partial(isPartial)
                .capacityFactor(dailyProduction.getProduction().doubleValue() / (factory.getCapacity() * dailyProduction.getCount()))
                .build();
    }

    interface TestConstants {
        UUID FOOD_FACTORY_ID = UUID.fromString("a18ba533-13fd-430d-a00a-5bccfab23de2");
        ZoneId TIME_ZONE = ZoneId.of("America/Los_Angeles");

        UUID UNKNOWN_ID = UUID.fromString("1adf62ae-70dc-4779-bfdd-4ac46315195d");

        Factory FOOD_FACTORY = Factory.builder()
                .id(FOOD_FACTORY_ID)
                .capacity(100)
                .timezone(TIME_ZONE)
                .build();

    }
}
