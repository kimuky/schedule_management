package com.example.schedule.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserRequestDto {

    private String id;
    private String password;
    private String name;
    private String email;
}
