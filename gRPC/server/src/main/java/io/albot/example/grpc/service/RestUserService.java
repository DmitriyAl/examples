package io.albot.example.grpc.service;

import io.albot.example.grpc.dao.UserDao;
import io.albot.example.grpc.dto.AddressDto;
import io.albot.example.grpc.dto.UserDto;
import io.albot.example.grpc.entity.User;
import io.albot.example.grpc.exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestUserService implements UserService {
    private final UserDao userDao;

    @Override
    public UserDto getUser(long id) {
        User user = userDao.findById(id).orElseThrow(() -> new NoSuchUserException(id));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .addresses(user.getAddresses().stream().map(a -> AddressDto.builder()
                        .id(a.getId())
                        .country(a.getCountry())
                        .city(a.getCity())
                        .street(a.getStreet())
                        .homeNumber(a.getHomeNumber())
                        .flatNumber(a.getFlatNumber())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}