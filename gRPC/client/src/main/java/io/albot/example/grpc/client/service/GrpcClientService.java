package io.albot.example.grpc.client.service;

import io.albot.example.grpc.GetUserRequest;
import io.albot.example.grpc.UserResponse;
import io.albot.example.grpc.UserServiceGrpc;
import io.albot.example.grpc.client.dto.Address;
import io.albot.example.grpc.client.dto.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GrpcClientService {
    public User getUser(long id) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        UserResponse userResponse = stub.getUser(GetUserRequest.newBuilder().setId(id).build());
        channel.shutdown();
        return unmarshalUser(userResponse);
    }

    private User unmarshalUser(UserResponse response) {
        return User.builder()
                .id(response.getId())
                .name(response.getName())
                .age(response.getAge())
                .addresses(response.getAddressesList().stream().map(r -> Address.builder()
                        .country(r.getCountry())
                        .city(r.getCity())
                        .street(r.getStreet())
                        .homeNumber(r.getHomeNumber())
                        .flatNumber(r.getFlatNumber())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}