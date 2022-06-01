package space.gavinklfong.exercise.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FactoryDailyProduction {
    LocalDate date;
    Integer production;
    Boolean partial;
    Double capacityFactor;
}
