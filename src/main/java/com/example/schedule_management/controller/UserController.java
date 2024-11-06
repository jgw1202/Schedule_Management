package com.example.schedule_management.controller;

import com.example.schedule_management.dto.SchedulerRequestDto;
import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.dto.UserRequestDto;
import com.example.schedule_management.dto.UserResponseDto;
import com.example.schedule_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    // 유저 등록
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {
        return new ResponseEntity<>(userService.saveUser(dto), HttpStatus.CREATED);
    }

    // 유저 조회
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    // 유저 수정
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateScheduler(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return new ResponseEntity<>(userService.updateUser(id, dto.getName(), dto.getEmail()), HttpStatus.OK);
    }

}
