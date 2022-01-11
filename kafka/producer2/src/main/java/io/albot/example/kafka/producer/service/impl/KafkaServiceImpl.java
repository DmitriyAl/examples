package io.albot.example.kafka.producer.service.impl;

import io.albot.example.kafka.dto.Message;
import io.albot.example.kafka.dto.User;
import io.albot.example.kafka.producer.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {
    private final KafkaTemplate<Long, User> kafkaTemplate;

    @Override
    public void send(Message<Long, User> message) throws InterruptedException {
        ListenableFuture<SendResult<Long, User>> future = kafkaTemplate.send(message.getTopic(), message.getPartition(),
                message.getKey(), message.getMessage());
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
