package space.gavinklfong.exercise.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import space.gavinklfong.exercise.dtos.FactoryDailyProduction;
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static space.gavinklfong.exercise.controllers.FactoryRestControllerIntegrationTest.TestConstants.*;
import static space.gavinklfong.exercise.controllers.FactoryRestControllerIntegrationTest.TestConstants.FOOD_FACTORY_ID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Testcontainers
@ActiveProfiles("test")
public class FactoryRestControllerIntegrationTest {

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
    private WebTestClient webTestClient;

    @Test
    void givenValidType_whenGetEnergyFarmByType_returnFarmList() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/factories")
                                .queryParam("type", FactoryType.TOY_FACTORY)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo(TOY_FACTORY.getId().toString())
                .jsonPath("$[0].name").isEqualTo(TOY_FACTORY.getName())
                .jsonPath("$[0].type").isEqualTo(TOY_FACTORY.getType().name())
                .jsonPath("$[0].capacity").isEqualTo(TOY_FACTORY.getCapacity())
                .jsonPath("$[0].timezone").isEqualTo(TOY_FACTORY.getTimezone().toString());
    }

    @Test
    void givenValidRequest_whenRetrieveDailyProduction_returnDailyProductionData() {
        LocalDate startDate = LocalDate.of(2022, 1, 15);
        LocalDate endDate = LocalDate.of(2022, 1, 16);

        FactoryDailyProduction item1 = FactoryDailyProduction.builder()
                .production(1028)
                .capacityFactor(0.45D)
                .partial(true)
                .date(startDate)
                .build();

        FactoryDailyProduction item2 = FactoryDailyProduction.builder()
                .production(1209)
                .capacityFactor(0.50D)
                .partial(false)
                .date(endDate)
                .build();

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(String.format("/factories/%s/daily-production", FOOD_FACTORY_ID))
                                .queryParam("startDate", startDate)
                                .queryParam("endDate", endDate)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].date").isEqualTo(item1.getDate().format(DateTimeFormatter.ISO_DATE))
                .jsonPath("$[0].production").isEqualTo(item1.getProduction())
                .jsonPath("$[0].capacityFactor")
                    .value(capacityFactor1 -> {
                        double capacityFactorDouble = ((BigDecimal)capacityFactor1).setScale(2, RoundingMode.HALF_UP).doubleValue();
                        assertThat(capacityFactorDouble).isEqualTo(item1.getCapacityFactor());
                    }, BigDecimal.class)
                .jsonPath("$[0].partial").isEqualTo(item1.getPartial())
                .jsonPath("$[1].date").isEqualTo(item2.getDate().format(DateTimeFormatter.ISO_DATE))
                .jsonPath("$[1].production").isEqualTo(item2.getProduction())
                .jsonPath("$[1].capacityFactor")
                .value(capacityFactor2 -> {
                    double capacityFactorDouble = ((BigDecimal)capacityFactor2).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    assertThat(capacityFactorDouble).isEqualTo(item2.getCapacityFactor());
                }, BigDecimal.class)
                .jsonPath("$[1].partial").isEqualTo(item2.getPartial());
    }

    interface TestConstants {
        UUID FOOD_FACTORY_ID = UUID.fromString("a18ba533-13fd-430d-a00a-5bccfab23de2");
        ZoneId TIME_ZONE = ZoneId.of("America/Los_Angeles");

        Factory FOOD_FACTORY = Factory.builder()
                .id(FOOD_FACTORY_ID)
                .type(FactoryType.FOOD_FACTORY)
                .name("UK Nestle Food Factory")
                .capacity(100)
                .timezone(TIME_ZONE)
                .build();

        Factory TOY_FACTORY = Factory.builder()
                .id(UUID.fromString("c9590ef7-ab84-4df9-a493-66d53664e0fb"))
                .type(FactoryType.TOY_FACTORY)
                .name("Lego Factory")
                .capacity(580)
                .timezone(ZoneId.of("Africa/Casablanca"))
                .build();

    }

}
