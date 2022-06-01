package space.gavinklfong.exercise.repositories.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyProduction {
    LocalDate date;
    Integer production;
    Integer count;
}
