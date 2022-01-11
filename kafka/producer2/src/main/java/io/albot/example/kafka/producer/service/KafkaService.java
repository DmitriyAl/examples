package io.albot.example.kafka.producer.service;

import io.albot.example.kafka.dto.Message;
import io.albot.example.kafka.dto.User;

public interface KafkaService {
    void send(Message<Long, User> message) throws InterruptedException;
}
