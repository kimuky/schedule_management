package com.example.schedule.service.user;

import com.example.schedule.dto.users.UserRequestDto;
import com.example.schedule.dto.users.UserResponseDto;

public interface UserService {


    UserResponseDto registerUser(UserRequestDto dto);
}
