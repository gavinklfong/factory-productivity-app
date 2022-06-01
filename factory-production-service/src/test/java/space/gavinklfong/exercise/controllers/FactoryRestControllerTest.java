package space.gavinklfong.exercise.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import space.gavinklfong.exercise.dtos.FactoryDailyProduction;
import space.gavinklfong.exercise.exceptions.RecordNotFoundException;
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;
import space.gavinklfong.exercise.services.FactoryService;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static space.gavinklfong.exercise.controllers.FactoryRestControllerTest.TestConstants.WIND_FARM;
import static space.gavinklfong.exercise.controllers.FactoryRestControllerTest.TestConstants.FACTORY_ID;

@WebFluxTest(controllers = {FactoryRestController.class})
@ActiveProfiles("test")
public class FactoryRestControllerTest {

    @MockBean
    private FactoryService factoryService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void givenValidType_whenGetEnergyFarmByType_returnFarmList() {
        when(factoryService.retrieveEnergyFarmsByType(FactoryType.FOOD_FACTORY)).thenReturn(Flux.just(WIND_FARM));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/factories")
                                .queryParam("type", FactoryType.FOOD_FACTORY)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo(WIND_FARM.getId().toString())
                .jsonPath("$[0].name").isEqualTo(WIND_FARM.getName())
                .jsonPath("$[0].capacity").isEqualTo(WIND_FARM.getCapacity())
                .jsonPath("$[0].timezone").isEqualTo(WIND_FARM.getTimezone().toString());
    }

    @Test
    void givenUnknownType_whenGetEnergyFarmByType_returnBadRequest() {
        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/factories")
                                .queryParam("type", "ABC")
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();

        verifyNoInteractions(factoryService);
    }

    @Test
    void givenNoRecordFound_whenGetEnergyFarmByType_returnBadRequest() {

        when(factoryService.retrieveEnergyFarmsByType(FactoryType.FOOD_FACTORY)).thenReturn(Flux.error(new RecordNotFoundException()));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/factories")
                                .queryParam("type", FactoryType.FOOD_FACTORY)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void givenValidRequest_whenRetrieveDailyProduction_returnDailyProductionData() {
        LocalDate startDate = LocalDate.of(2022, 1, 15);
        LocalDate endDate = LocalDate.of(2022, 1, 16);

        FactoryDailyProduction item1 = FactoryDailyProduction.builder()
                .production(100)
                .capacityFactor(0.75)
                .partial(false)
                .date(startDate)
                .build();

        FactoryDailyProduction item2 = FactoryDailyProduction.builder()
                .production(200)
                .capacityFactor(0.45)
                .partial(true)
                .date(endDate)
                .build();

        when(factoryService.retrieveDailyProduction(FACTORY_ID, startDate, endDate))
                .thenReturn(Flux.just(item1, item2));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(String.format("/factories/%s/daily-production", FACTORY_ID))
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
                .jsonPath("$[0].capacityFactor").isEqualTo(item1.getCapacityFactor())
                .jsonPath("$[0].partial").isEqualTo(item1.getPartial())
                .jsonPath("$[1].date").isEqualTo(item2.getDate().format(DateTimeFormatter.ISO_DATE))
                .jsonPath("$[1].production").isEqualTo(item2.getProduction())
                .jsonPath("$[1].capacityFactor").isEqualTo(item2.getCapacityFactor())
                .jsonPath("$[1].partial").isEqualTo(item2.getPartial());
    }

    @Test
    void givenUnknownWindFarmId_whenRetrieveDailyProduction_returnBadRequestStatus() {
        LocalDate startDate = LocalDate.of(2022, 1, 15);
        LocalDate endDate = LocalDate.of(2022, 1, 16);

        when(factoryService.retrieveDailyProduction(FACTORY_ID, startDate, endDate))
                .thenReturn(Flux.error(new RecordNotFoundException()));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(String.format("/factories/%s/daily-production", FACTORY_ID))
                                .queryParam("startDate", startDate)
                                .queryParam("endDate", endDate)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void givenDailyProductionNoRecordFound_whenRetrieveDailyProduction_returnBadRequest() {
        LocalDate startDate = LocalDate.of(2022, 1, 15);
        LocalDate endDate = LocalDate.of(2022, 1, 16);

        when(factoryService.retrieveDailyProduction(FACTORY_ID, startDate, endDate))
                .thenReturn(Flux.error(new RecordNotFoundException()));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(String.format("/factories/%s/daily-production", FACTORY_ID))
                                .queryParam("startDate", startDate)
                                .queryParam("endDate", endDate)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @ParameterizedTest
    @MethodSource("invalidRequestTestArguments")
    void givenInvalidRequest_whenRetrieveDailyProduction_returnBadRequestStatus(String farmId, Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(String.format("/factories/%s/daily-production", farmId))
                                .queryParamIfPresent("startDate", startDate)
                                .queryParamIfPresent("endDate", endDate)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();

        verifyNoInteractions(factoryService);
    }

    private static Stream<Arguments> invalidRequestTestArguments() {
        return Stream.of(
                Arguments.arguments(FACTORY_ID.toString(), Optional.of(LocalDate.now().plusDays(1)), Optional.of(LocalDate.now().minusDays(1))),
                Arguments.arguments(FACTORY_ID.toString(), Optional.of(LocalDate.now().minusDays(1)), Optional.of(LocalDate.now().plusDays(1))),
                Arguments.arguments(FACTORY_ID.toString(), Optional.of(LocalDate.now().plusDays(1)), Optional.of(LocalDate.now().plusDays(2))),
                Arguments.arguments(FACTORY_ID.toString(), Optional.of(LocalDate.now().minusDays(1)), Optional.of(LocalDate.now().minusDays(2))),
                Arguments.arguments(FACTORY_ID.toString(), Optional.empty(), Optional.of(LocalDate.now().minusDays(1))),
                Arguments.arguments(FACTORY_ID.toString(), Optional.of(LocalDate.now().minusDays(1)), Optional.empty()),
                Arguments.arguments(FACTORY_ID.toString(), Optional.empty(), Optional.empty()),
                Arguments.arguments("ABC", Optional.of(LocalDate.now().minusDays(1)), Optional.of(LocalDate.now()))
        );
    }

    interface TestConstants {
        UUID FACTORY_ID = UUID.fromString("a18ba533-13fd-430d-a00a-5bccfab23de2");
        ZoneId TIME_ZONE = ZoneId.of("America/Los_Angeles");

        Factory WIND_FARM = Factory.builder()
                .id(FACTORY_ID)
                .name("UK Nestle Food Factory")
                .capacity(100)
                .timezone(TIME_ZONE)
                .build();
    }
}
