package io.albot.example.grpc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer homeNumber;
    private Integer flatNumber;
}