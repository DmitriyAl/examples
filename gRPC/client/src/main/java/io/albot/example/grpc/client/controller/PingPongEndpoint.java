package io.albot.example.grpc.client.controller;

import io.albot.example.grpc.client.service.GrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongEndpoint {
    private final GrpcClientService grpcClientService;

    @Autowired
    public PingPongEndpoint(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @GetMapping("/ping")
    public String ping() {
        return grpcClientService.ping();
    }
}