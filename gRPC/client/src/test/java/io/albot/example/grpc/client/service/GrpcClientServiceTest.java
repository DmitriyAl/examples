package io.albot.example.grpc.client.service;

import io.albot.example.grpc.client.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GrpcClientServiceTest {
    @Autowired
    private GrpcClientService grpcClientService;

    @Test
    void getUser() {
        User user = grpcClientService.getUser(1L);
        assertThat(user.getId()).isEqualTo(1L);
    }
}