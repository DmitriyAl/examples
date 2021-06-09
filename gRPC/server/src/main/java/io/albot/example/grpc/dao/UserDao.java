package io.albot.example.grpc.dao;

import io.albot.example.grpc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}