package com.example.schedule_management.service;

import com.example.schedule_management.dto.UserRequestDto;
import com.example.schedule_management.dto.UserResponseDto;



public interface UserService {
    UserResponseDto saveUser(UserRequestDto dto);

    UserResponseDto findUserById(Long id);
}
