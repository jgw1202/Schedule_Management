package com.example.schedule_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Scheduler {
    @Setter
    private Long id;
    private Long userId;
    private String password;
    private String userName;
    private String contents;
    private Date createdAt;
    private Date updatedAt;

    public Scheduler(String password, String userName, String contents) {
        this.password = password;
        this.userName = userName;
        this.contents = contents;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public void update(String userName, String contents) {
        this.userName = userName;
        this.contents = contents;
        this.updatedAt = new Date();
    }
}
