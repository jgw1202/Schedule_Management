package com.example.schedule_management.service;

import com.example.schedule_management.dto.UserRequestDto;
import com.example.schedule_management.dto.UserResponseDto;
import com.example.schedule_management.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponseDto saveUser(UserRequestDto dto);
}
