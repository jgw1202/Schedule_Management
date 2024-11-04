package com.example.schedule_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Scheduler {

    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private Date createdAt;
    private Date updatedAt;

}
