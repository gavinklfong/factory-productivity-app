package space.gavinklfong.exercise.repositories;

import lombok.extern.slf4j.Slf4j;
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
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static space.gavinklfong.exercise.repositories.FactoryRepositoryTest.TestConstants.*;

@Slf4j
@Testcontainers
@ActiveProfiles("test")
@DataR2dbcTest
public class FactoryRepositoryTest {

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
    private FactoryRepository repo;

    @Test
    void testRepositoryExists() {
        assertThat(repo).isNotNull();
    }

    @Test
    void testFindById() {
        Mono<Factory> windFarmMono = repo.findById(FOOD_FACTORY_ID);
        assertThat(windFarmMono).isNotNull();
        StepVerifier.create(windFarmMono)
                .expectNext(FOOD_FACTORY)
                .expectComplete();
    }

    @Test
    void testFindByUnknownId() {
        Mono<Factory> windFarmMono = repo.findById(UNKNOWN_ID);
        assertThat(windFarmMono).isNotNull();
        StepVerifier.create(windFarmMono)
                .expectNextCount(0)
                .expectComplete();
    }

    @Test
    void testFindByType() {
        Flux<Factory> farmFlux = repo.findByTypeOrderByNameAsc(FactoryType.TOY_FACTORY);
        assertThat(farmFlux).isNotNull();
        StepVerifier.create(farmFlux)
                .expectNext(TOY_FACTORY)
                .verifyComplete();
    }

    interface TestConstants {
        UUID FOOD_FACTORY_ID = UUID.fromString("a18ba533-13fd-430d-a00a-5bccfab23de2");

        UUID UNKNOWN_ID = UUID.fromString("1adf62ae-70dc-4779-bfdd-4ac46315195d");
        Factory FOOD_FACTORY = Factory.builder()
                .id(FOOD_FACTORY_ID)
                .type(FactoryType.FOOD_FACTORY)
                .capacity(100)
                .name("California Bunge Food Factory")
                .timezone(ZoneId.of("America/Los_Angeles"))
                .build();

        Factory TOY_FACTORY = Factory.builder()
                .id(UUID.fromString("c9590ef7-ab84-4df9-a493-66d53664e0fb"))
                .capacity(580)
                .timezone(ZoneId.of("Africa/Casablanca"))
                .name("Lego Factory")
                .type(FactoryType.TOY_FACTORY)
                .build();
    }
}
