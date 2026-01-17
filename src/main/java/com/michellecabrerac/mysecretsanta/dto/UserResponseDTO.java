package com.michellecabrerac.mysecretsanta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private LocalDateTime createdAt;
}
