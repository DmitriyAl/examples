package io.albot.example.webflux.dao;

import io.albot.example.webflux.entity.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {
    Mono<Department> findByUserId(Integer userId);
}