package space.gavinklfong.exercise.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import space.gavinklfong.exercise.models.Factory;
import space.gavinklfong.exercise.models.FactoryType;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface FactoryRepository extends ReactiveCrudRepository<Factory, UUID> {
    Flux<Factory> findByTypeOrderByNameAsc(FactoryType type);
}
