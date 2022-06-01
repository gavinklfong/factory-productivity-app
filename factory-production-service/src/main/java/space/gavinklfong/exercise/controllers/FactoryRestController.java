package space.gavinklfong.exercise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;
import space.gavinklfong.exercise.services.FactoryService;
import space.gavinklfong.exercise.dtos.FactoryDailyProduction;
import reactor.core.publisher.Flux;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/factories")
public class FactoryRestController {

    @Autowired
    private FactoryService factoryService;

    @GetMapping
    public Flux<Factory> retrieveFactories(@RequestParam FactoryType type) {
        return factoryService.retrieveEnergyFarmsByType(type);
    }

    @GetMapping("/{factoryId}/daily-production")
    public Flux<FactoryDailyProduction> retrieveDailyProduction(@PathVariable UUID factoryId,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        validateDateRange(startDate, endDate);
        return factoryService.retrieveDailyProduction(factoryId, startDate, endDate);
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(LocalDate.now()) || endDate.isAfter(LocalDate.now())) throw new ValidationException("[Start Date] and [End Date] should be present date or in the past");
        if (startDate.isAfter(endDate)) throw new ValidationException("[Start Date] should be before or equal to [End Date]");
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.badRequest().build();
    }
}
