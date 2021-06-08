package io.albot.example.grpc.proto.service;

import io.albot.example.grpc.proto.HelloRequest;
import io.albot.example.grpc.proto.HelloResponse;
import io.albot.example.grpc.proto.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String greeting = "Hello, " + request.getFirstName() + " " + request.getLastName();

        HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}