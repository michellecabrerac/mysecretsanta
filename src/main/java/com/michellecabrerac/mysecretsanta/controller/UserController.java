package com.michellecabrerac.mysecretsanta.controller;

import com.michellecabrerac.mysecretsanta.dto.UserDTO;
import com.michellecabrerac.mysecretsanta.dto.UserResponseDTO;
import com.michellecabrerac.mysecretsanta.mapper.UserMapper;
import com.michellecabrerac.mysecretsanta.model.User;
import com.michellecabrerac.mysecretsanta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO userDto){
        User user = userService.createUser(
                userDto.getEmail(),
                userDto.getName(),
                userDto.getSurname()
                );
        UserResponseDTO response = userMapper.toResponseDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        UserResponseDTO response = userMapper.toResponseDto(user);
        return ResponseEntity.ok(response);
    }
}
