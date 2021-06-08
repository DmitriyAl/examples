package io.test.kafka.kafka.dto;

import lombok.Data;

@Data
public class Message<ID, V> {
    private ID id;
    private V message;
}