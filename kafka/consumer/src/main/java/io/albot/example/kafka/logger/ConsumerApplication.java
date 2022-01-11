package io.albot.example.kafka.logger;

import io.albot.example.kafka.dto.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableKafka
public class ConsumerApplication {
    private final AtomicInteger firstProducerCounter = new AtomicInteger();
    private final AtomicInteger secondProducerCounter = new AtomicInteger();

    @KafkaListener(topics = "msg")
    public void consume(ConsumerRecord<Long, User> record) {
        System.out.println("Partition: " + record.partition());
        System.out.println("Key: " + record.key());
        System.out.println("Payload: " + record.value());
        if (record.value().getName().equals("Producer1")) {
            firstProducerCounter.incrementAndGet();
        } else if (record.value().getName().equals("Producer2")) {
            secondProducerCounter.incrementAndGet();
        }
            System.out.println("Producer1 counter: " + firstProducerCounter.get());
            System.out.println("Producer2 counter: " + secondProducerCounter.get());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
