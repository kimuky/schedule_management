package com.example.schedule.repository.user;

import com.example.schedule.dto.user.UserRequestDto;

public interface UserRepository {
    int registerUser(UserRequestDto dto);
}
