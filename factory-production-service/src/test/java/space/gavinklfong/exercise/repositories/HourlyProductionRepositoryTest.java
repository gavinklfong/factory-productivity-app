package space.gavinklfong.exercise.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import space.gavinklfong.exercise.repositories.dtos.DailyProduction;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static space.gavinklfong.exercise.repositories.HourlyProductionRepositoryTest.TestConstants.*;
import static space.gavinklfong.exercise.repositories.FactoryRepositoryTest.TestConstants.FOOD_FACTORY_ID;

@Slf4j
@Testcontainers
@ActiveProfiles("test")
@DataR2dbcTest
public class HourlyProductionRepositoryTest {

    @Container
    public static JdbcDatabaseContainer container = new PostgreSQLContainer("postgres")
            .withInitScript("database_init.sql");
    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () -> String.format("r2dbc:postgresql://%s:%s/%s", container.getContainerIpAddress(), container.getMappedPort(5432), container.getDatabaseName()));
        registry.add("spring.r2dbc.username", () -> container.getUsername());
        registry.add("spring.r2dbc.password", () -> container.getPassword());
    }

    @Autowired
    private HourlyProductionRepository repo;

    @Test
    void testRepositoryExists() {
        assertThat(repo).isNotNull();
    }

    @DisplayName("Given a date range with data available, when get daily production, then a correct aggregated daily data is returned")

    @Test
    void givenDateRange_whenAggregateDailyProduction_thenReturnAggregatedDailyProduction() {
        Flux<DailyProduction> dailyProductionFlux = repo.aggregateDailyProduction(FOOD_FACTORY_ID, START_DATE, END_DATE, TIMEZONE);
        assertThat(dailyProductionFlux).isNotNull();
        StepVerifier
                .create(dailyProductionFlux)
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_ITEM_1))
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_ITEM_2))
                .verifyComplete();
    }

    @DisplayName("Given day-light saving transition (clock forward) in the date range, when get daily production, then a correct aggregated daily data is returned")
    @Test
    void givenDateRangeWithForwardDST_whenAggregateDailyProduction_thenReturnAggregatedDailyProduction() {
        Flux<DailyProduction> dailyProductionFlux = repo.aggregateDailyProduction(FOOD_FACTORY_ID, DST_FWD_START_DATE, DST_FWD_END_DATE, TIMEZONE);
        assertThat(dailyProductionFlux).isNotNull();
        StepVerifier
                .create(dailyProductionFlux)
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_DST_FWD_1))
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_DST_FWD_2))
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_DST_FWD_3))
                .verifyComplete();
    }

    @DisplayName("Given day-light saving transition (clock backward) in the date range, when get daily production, then a correct aggregated daily data is returned")
    @Test
    void givenDateRangeWithBackwardDST_whenAggregateDailyProduction_thenReturnAggregatedDailyProduction() {
        Flux<DailyProduction> dailyProductionFlux = repo.aggregateDailyProduction(FOOD_FACTORY_ID, DST_BWD_START_DATE, DST_BWD_END_DATE, TIMEZONE);
        assertThat(dailyProductionFlux).isNotNull();
        StepVerifier
                .create(dailyProductionFlux)
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_DST_BWD_1))
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_DST_BWD_2))
                .expectNextMatches(item -> item.equals(DAILY_PRODUCTION_DST_BWD_3))
                .verifyComplete();
    }

    @DisplayName("Given an unknown farm Id (i.e. no available production data) , when get daily production, then return empty")

    @Test
    void givenUnknownFarmId_whenAggregateDailyProduction_thenReturnEmptyResult() {
        Flux<DailyProduction> dailyProductionFlux = repo.aggregateDailyProduction(UUID.randomUUID(), START_DATE, END_DATE, TIMEZONE);
        assertThat(dailyProductionFlux).isNotNull();
        StepVerifier
                .create(dailyProductionFlux)
                .verifyComplete();
    }

    interface TestConstants {
        UUID WIND_FARM_ID = UUID.fromString("a18ba533-13fd-430d-a00a-5bccfab23de2");
        ZoneId TIMEZONE = ZoneId.of("America/Los_Angeles");

        // Expected daily production result of date range overlapped with DST (move forward 1 hour)
        LocalDate DST_FWD_START_DATE = LocalDate.of(2021, 3, 13);
        DailyProduction DAILY_PRODUCTION_DST_FWD_1 = DailyProduction.builder()
                .production(1231)
                .count(24)
                .date(DST_FWD_START_DATE)
                .build();
        DailyProduction DAILY_PRODUCTION_DST_FWD_2 = DailyProduction.builder()
                .production(1063)
                .count(23)
                .date(LocalDate.of(2021, 3, 14))
                .build();

        LocalDate DST_FWD_END_DATE = LocalDate.of(2021, 3, 15);
        DailyProduction DAILY_PRODUCTION_DST_FWD_3 = DailyProduction.builder()
                .production(1319)
                .count(24)
                .date(DST_FWD_END_DATE)
                .build();

        // Expected daily production result of date range overlapped with DST (move backward 1 hour)
        LocalDate DST_BWD_START_DATE = LocalDate.of(2021, 11, 6);
        DailyProduction DAILY_PRODUCTION_DST_BWD_1 = DailyProduction.builder()
                .production(1261)
                .count(24)
                .date(DST_BWD_START_DATE)
                .build();

        DailyProduction DAILY_PRODUCTION_DST_BWD_2 = DailyProduction.builder()
                .production(1173)
                .count(25)
                .date(LocalDate.of(2021, 11, 7))
                .build();

        LocalDate DST_BWD_END_DATE = LocalDate.of(2021, 11, 8);
        DailyProduction DAILY_PRODUCTION_DST_BWD_3 = DailyProduction.builder()
                .production(1144)
                .count(24)
                .date(DST_BWD_END_DATE)
                .build();


        // Expected daily production result of date range without change of DST
        LocalDate START_DATE = LocalDate.of(2022, 1, 15);
        LocalDate END_DATE = LocalDate.of(2022, 1, 16);
        DailyProduction DAILY_PRODUCTION_ITEM_1 = DailyProduction.builder()
                .production(1028)
                .count(23)
                .date(START_DATE)
                .build();
        DailyProduction DAILY_PRODUCTION_ITEM_2 = DailyProduction.builder()
                .production(1209)
                .count(24)
                .date(END_DATE)
                .build();
    }
}
