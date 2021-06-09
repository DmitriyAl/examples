package io.albot.example.grpc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer homeNumber;
    private Integer flatNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}