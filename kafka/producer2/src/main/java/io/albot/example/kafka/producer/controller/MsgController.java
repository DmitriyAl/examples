package io.albot.example.kafka.producer.controller;

import io.albot.example.kafka.dto.Message;
import io.albot.example.kafka.dto.User;
import io.albot.example.kafka.producer.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
@RequiredArgsConstructor
public class MsgController {
    private final KafkaService kafkaService;

    @PostMapping
    public void sendOrder(@RequestBody Message<Long, User> message) throws InterruptedException {
        kafkaService.send(message);
    }
}