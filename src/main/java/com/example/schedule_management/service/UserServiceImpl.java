
package com.example.schedule_management.service;


import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.dto.UserRequestDto;
import com.example.schedule_management.dto.UserResponseDto;
import com.example.schedule_management.entity.Scheduler;
import com.example.schedule_management.entity.User;
import com.example.schedule_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail());
        return userRepository.saveUser(user);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        Optional<User> User = userRepository.findUesrById(id);
        if (User.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new UserResponseDto(User.get());
    }
}
