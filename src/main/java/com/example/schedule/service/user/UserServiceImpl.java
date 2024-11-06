package com.example.schedule.service.user;

import com.example.schedule.dto.users.UserRequestDto;
import com.example.schedule.dto.users.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {

        User user = new User(dto);

        int updatedRow = userRepository.registerUser(user);

        if (updatedRow == 0) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 없습니다");
        }

        //TODO uid 는 어떻게 반환?
        // 여기서 findById로 찾기?
        return new UserResponseDto(user.getId()) ;
    }
}