package com.example.schedule_management.repository;

import com.example.schedule_management.dto.UserResponseDto;
import com.example.schedule_management.entity.User;

import java.util.Optional;

public interface UserRepository {

    UserResponseDto saveUser(User User);

    Optional<User> findUesrById(Long id);

    int updateUser(Long id, String name, String email);
}
