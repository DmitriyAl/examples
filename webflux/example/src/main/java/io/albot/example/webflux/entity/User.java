package io.albot.example.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    private Integer id;
    private String name;
    private int age;
    private double salary;
}