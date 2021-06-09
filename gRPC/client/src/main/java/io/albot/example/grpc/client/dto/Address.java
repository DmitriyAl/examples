package io.albot.example.grpc.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Address {
    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer homeNumber;
    private Integer flatNumber;
}