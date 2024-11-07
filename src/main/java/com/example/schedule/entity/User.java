package com.example.schedule.entity;

import com.example.schedule.dto.user.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private String uid;
    private String id;
    private String password;
    private String name;
    private String email;

    public User(UserRequestDto dto) {
        this.id = dto.getId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.email = dto.getEmail();
    }
}
