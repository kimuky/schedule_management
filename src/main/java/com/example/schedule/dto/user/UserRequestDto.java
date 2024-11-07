package com.example.schedule.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank(message = "아이디는 꼭 입력해주세요")
    private String id;

    @NotBlank(message = "패스워드는 꼭 입력해주세요")
    private String password;

    @NotBlank(message = "이름은 꼭 입력해주세요")
    private String name;

    @Email (message = "이메일을 제대로 입력해주세요")
    private String email;
}
