package com.example.schedule_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Scheduler {

    private Long id;
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
}
