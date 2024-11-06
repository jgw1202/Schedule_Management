package com.example.schedule_management.repository;

import com.example.schedule_management.dto.UserResponseDto;
import com.example.schedule_management.entity.User;

public interface UserRepository {

    UserResponseDto saveUser(User User);
}
