package io.albot.example.grpc.controller;

import io.albot.example.grpc.dto.UserDto;
import io.albot.example.grpc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}