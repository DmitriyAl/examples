package io.albot.example.grpc.client.service;

import io.albot.example.grpc.PingPongServiceGrpc;
import io.albot.example.grpc.PingRequest;
import io.albot.example.grpc.PongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {
    public String ping() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        PingPongServiceGrpc.PingPongServiceBlockingStub stub
                = PingPongServiceGrpc.newBlockingStub(channel);
        PongResponse helloResponse = stub.ping(PingRequest.newBuilder()
                .setPing("ping")
                .build());
        channel.shutdown();
        return helloResponse.getPong();
    }
}