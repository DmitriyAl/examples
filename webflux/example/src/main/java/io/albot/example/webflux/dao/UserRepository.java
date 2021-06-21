package io.albot.example.webflux.dao;

import io.albot.example.webflux.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
    Flux<User> findAllByAgeGreaterThanEqual(int age);
}