package io.test.kafka.kafka.controller;

import io.test.kafka.kafka.dto.Message;
import io.test.kafka.kafka.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {
    private final KafkaTemplate<Long, User> kafkaTemplate;

    @Autowired
    public MsgController(KafkaTemplate<Long, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendOrder(@RequestBody Message<Long, User> message){
        ListenableFuture<SendResult<Long, User>> future = kafkaTemplate.send("msg", message.getId(), message.getMessage());
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}