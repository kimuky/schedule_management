package com.example.schedule.service.user;

import com.example.schedule.dto.user.UserRequestDto;
import com.example.schedule.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserRequestDto dto);
}
