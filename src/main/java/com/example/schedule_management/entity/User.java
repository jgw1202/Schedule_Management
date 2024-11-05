package com.example.schedule_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class User {
    @Setter
    private Long id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public void setUpdatedAt() {
        this.updatedAt = new Date();
    }
}
