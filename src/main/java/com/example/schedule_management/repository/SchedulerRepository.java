package com.example.schedule_management.repository;

import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;

import java.util.List;

public interface SchedulerRepository {
    Scheduler saveScheduler(Scheduler scheduler);

    List<SchedulerResponseDto> findAllSchedulers();

    Scheduler findSchedulerById(Long id);
}
