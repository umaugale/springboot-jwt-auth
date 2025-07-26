package com.jwtJpa.jwtJpa.DTO;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class UserDto {
    private String email;
    private String name;
    private Long mobileNo;
    private LocalDate createdAt;

}
