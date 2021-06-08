package io.albot.example.webflux.controller;

import io.albot.example.webflux.dto.UserDepartmentDTO;
import io.albot.example.webflux.entity.User;
import io.albot.example.webflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable Integer userId) {
        Mono<User> user = userService.findById(userId);
        return user.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<User>> updateUserById(@PathVariable Integer userId, @RequestBody User user) {
        return userService.updateUser(userId, user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable Integer userId) {
        return userService.deleteUser(userId)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/age/{age}")
    public Flux<User> getUsersByAge(@PathVariable int age) {
        return userService.findUsersByAge(age);
    }

    @PostMapping("/search/id")
    public Flux<User> fetchUsersByIds(@RequestBody List<Integer> ids) {
        return userService.fetchUsers(ids);
    }

    @GetMapping("/{userId}/department")
    public Mono<UserDepartmentDTO> fetchUserAndDepartment(@PathVariable Integer userId) {
        return userService.fetchUserAndDepartment(userId);
    }
}