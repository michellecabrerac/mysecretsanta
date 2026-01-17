package com.michellecabrerac.mysecretsanta.mapper;

import com.michellecabrerac.mysecretsanta.dto.UserResponseDTO;
import com.michellecabrerac.mysecretsanta.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toResponseDto(User user) {
        if (user == null){
            return null;
        }
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
