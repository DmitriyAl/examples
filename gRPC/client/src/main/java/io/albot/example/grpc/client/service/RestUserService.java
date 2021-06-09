package io.albot.example.grpc.client.service;

import io.albot.example.grpc.client.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class RestUserService implements UserService {
    private final RestTemplate restTemplate;

    @Autowired
    public RestUserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User getUser(long id) {
        URI uri = URI.create("http://localhost:8081/user/" + id);
        return restTemplate.getForObject(uri, User.class);
    }
}
