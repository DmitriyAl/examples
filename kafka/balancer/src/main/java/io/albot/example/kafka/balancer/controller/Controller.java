package io.albot.example.kafka.balancer.controller;

import io.albot.example.kafka.dto.Message;
import io.albot.example.kafka.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final WebClient webClient;

    @PostMapping
    public void sendToProducers(@RequestBody Message<Long, User> message) {
        Random random = new Random();
        String name = message.getMessage().getName();
        for (int i = 0; i < 1000; i++) {
            message.getMessage().setName(name + i);
            if (random.nextBoolean()) {
                webClient.post().uri("http://localhost:8081/msg").bodyValue(message).retrieve().bodyToMono(Void.class).subscribe();
            } else {
                webClient.post().uri("http://localhost:8082/msg").bodyValue(message).retrieve().bodyToMono(Void.class).subscribe();
            }
        }
        System.out.println("finished");
    }
}
