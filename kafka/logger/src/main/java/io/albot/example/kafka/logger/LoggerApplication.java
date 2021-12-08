package io.albot.example.kafka.logger;

import io.albot.example.kafka.dto.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableKafka
public class LoggerApplication {

    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<Long, User> record) throws InterruptedException {
        System.out.println("Partition: " + record.partition());
        System.out.println("Key: " + record.key());
        System.out.println("Payload: " + record.value());
    }

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
    }

}
