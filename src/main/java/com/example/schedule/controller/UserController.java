package com.example.schedule.controller;

import com.example.schedule.dto.users.UserRequestDto;
import com.example.schedule.dto.users.UserResponseDto;
import com.example.schedule.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser (@RequestBody UserRequestDto dto) {

        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.OK);
    }
}