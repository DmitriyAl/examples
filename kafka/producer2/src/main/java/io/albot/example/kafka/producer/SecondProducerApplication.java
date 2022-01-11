package io.albot.example.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SecondProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondProducerApplication.class, args);
    }

}
