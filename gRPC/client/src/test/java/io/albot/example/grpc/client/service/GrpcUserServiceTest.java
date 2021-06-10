package io.albot.example.grpc.client.service;

import io.albot.example.grpc.client.dto.User;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class GrpcUserServiceTest {
    private static GrpcUserService grpcUserService;
    private static final int attempts = 10000;

    @BeforeAll
    static void setup() {
        grpcUserService = new GrpcUserService(ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build());
    }

    @AfterAll
    static void tearDown() {
        grpcUserService.shutdown();
    }

    @RepeatedTest(10)
    void getUsersAsynchronously() {
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for (int i = 0; i < attempts; i++) {
            executorService.execute(() -> {
                for (int j = 1; j <= 5; j++) {
                    User user = grpcUserService.getUser(j);
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
                User user = grpcUserService.getUser(j);
                assertThat(user.getId()).isEqualTo(j);
                assertThat(user.getName()).isNotEmpty();
            }
        }
    }
}