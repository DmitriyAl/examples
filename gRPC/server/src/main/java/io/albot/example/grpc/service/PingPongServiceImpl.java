package io.albot.example.grpc.service;

import io.albot.example.grpc.PingPongServiceGrpc;
import io.albot.example.grpc.PingRequest;
import io.albot.example.grpc.PongResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void ping(
            PingRequest request, StreamObserver<PongResponse> responseObserver) {
        String ping = new StringBuilder()
                .append("pong")
                .toString();
        PongResponse response = PongResponse.newBuilder()
                .setPong(ping)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}