package io.albot.example.grpc.client.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private Long id;
    private Integer age;
    private String name;
    private List<Address> addresses;
}