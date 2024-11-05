package com.example.schedule_management.dto;

import com.example.schedule_management.entity.Scheduler;
import com.example.schedule_management.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SchedulerResponseDto {
    private Long id;
    private String userName;
    private String contents;
    private Date createdAt;
    private Date updatedAt;

    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.userName = scheduler.getUserName();
        this.contents = scheduler.getContents();
        this.createdAt = scheduler.getCreatedAt();
        this.updatedAt = scheduler.getUpdatedAt();
    }
}
