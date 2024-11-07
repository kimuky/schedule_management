package com.example.schedule.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String uid;
    private String id;
    private String password;
    private String name;
    private String email;

    public UserResponseDto(String id) {
        this.id = id;
    }
}
