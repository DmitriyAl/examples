package io.albot.example.grpc.service;

import io.albot.example.grpc.AddressResponse;
import io.albot.example.grpc.GetUserRequest;
import io.albot.example.grpc.UserResponse;
import io.albot.example.grpc.UserServiceGrpc;
import io.albot.example.grpc.dao.UserDao;
import io.albot.example.grpc.entity.Address;
import io.albot.example.grpc.entity.User;
import io.albot.example.grpc.exception.NoSuchUserException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private final UserDao userDao;

    @Override
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userDao.findById(request.getId()).orElseThrow(() -> new NoSuchUserException(request.getId()));
        List<Address> addresses = user.getAddresses();
        UserResponse response = UserResponse.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setAge(user.getAge())
                .addAllAddresses(addresses.stream().map(a -> AddressResponse.newBuilder()
                        .setCountry(a.getCountry())
                        .setCity(a.getCity())
                        .setStreet(a.getStreet())
                        .setHomeNumber(a.getHomeNumber())
                        .setFlatNumber(a.getFlatNumber())
                        .build()).collect(Collectors.toList()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}