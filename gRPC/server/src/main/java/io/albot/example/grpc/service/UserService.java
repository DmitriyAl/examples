package io.albot.example.grpc.service;

import io.albot.example.grpc.dto.UserDto;

public interface UserService {
    UserDto getUser(long id);
}