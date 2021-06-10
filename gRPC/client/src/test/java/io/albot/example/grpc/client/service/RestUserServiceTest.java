package io.albot.example.grpc.client.service;

import io.albot.example.grpc.client.dto.User;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestUserServiceTest {
    @Autowired
    private RestUserService restUserService;
    private static final int attempts = 10000;

    @RepeatedTest(10)
    void getUsersAsynchronously() {
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for (int i = 0; i < attempts; i++) {
            executorService.execute(() -> {
                for (int j = 1; j <= 5; j++) {
                    User user = restUserService.getUser(j);
                    assertThat(user.getId()).isEqualTo(j);
                    assertThat(user.getName()).isNotEmpty();
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RepeatedTest(10)
    void getUsersSynchronously() {
        for (int i = 0; i < attempts; i++) {
            for (int j = 1; j <= 5; j++) {
                User user = restUserService.getUser(j);
                assertThat(user.getId()).isEqualTo(j);
                assertThat(user.getName()).isNotEmpty();
            }
        }
    }
}