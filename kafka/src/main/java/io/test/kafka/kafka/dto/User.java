package io.test.kafka.kafka.dto;

import lombok.Data;

@Data
public class User {
    private Long age;
    private String name;
    private Address address;
}