package space.gavinklfong.exercise.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("hourly_production")
public class HourlyProduction {
    @Id
    Integer id;
    UUID factoryId;

    @Column("start_hour")
    Instant startHour;
    Integer production;
}
