package com.example.schedule_management.dto;

import com.example.schedule_management.entity.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
