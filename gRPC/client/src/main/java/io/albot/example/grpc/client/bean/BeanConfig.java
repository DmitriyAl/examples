package io.albot.example.grpc.client.bean;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ManagedChannel managedChanel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
    }
}