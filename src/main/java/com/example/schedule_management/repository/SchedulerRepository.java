package com.example.schedule_management.repository;

import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;

import java.util.List;
import java.util.Optional;

public interface SchedulerRepository {
    SchedulerResponseDto saveScheduler(Scheduler scheduler);

    List<SchedulerResponseDto> findAllSchedulers();

    Optional<Scheduler> findSchedulerById(Long id);

    int updateScheduler(Long id, String userName, String contents);

    int deleteScheduler(Long id);
}
