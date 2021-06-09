package io.albot.example.grpc.client.service;

import io.albot.example.grpc.client.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestUserServiceTest {
    @Autowired
    private RestUserService restUserService;

    @Test
    void getUser() {
        User user = restUserService.getUser(1L);
        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    void getUsers() {
        System.out.println(new Date());
        for (int i = 0; i < 1000; i++) {
            for (int j = 1; j <= 5; j++) {
                restUserService.getUser(j);
            }
        }
        System.out.println(new Date());
    }
}