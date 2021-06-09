package io.albot.example.grpc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private Integer age;
    private String name;
    private List<AddressDto> addresses;
}