package io.albot.example.webflux.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDepartmentDTO {
    private int age;
    private Integer departmentId;
    private String departmentName;
    private String userName;
    private Integer userId;
    private String loc;
    private double salary;
}