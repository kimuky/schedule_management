package com.example.schedule.repository.user;

import com.example.schedule.dto.users.UserResponseDto;
import com.example.schedule.entity.User;


public interface UserRepository {
    int registerUser(User user);
}
