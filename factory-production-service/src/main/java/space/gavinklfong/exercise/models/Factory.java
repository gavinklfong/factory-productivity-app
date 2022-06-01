package space.gavinklfong.exercise.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZoneId;
import java.util.UUID;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("factory")
public class Factory {
    @Id
    UUID id;
    FactoryType type;
    String name;
    Integer capacity;
    ZoneId timezone;
}
