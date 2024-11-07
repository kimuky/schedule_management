package com.example.schedule.repository.user;

import com.example.schedule.dto.user.UserRequestDto;
import com.example.schedule.entity.User;


public interface UserRepository {
    int registerUser(UserRequestDto dto);
}
