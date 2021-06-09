package io.albot.example.grpc.client.service;

import io.albot.example.grpc.client.dto.User;

public interface UserService {
    User getUser(long id);
}