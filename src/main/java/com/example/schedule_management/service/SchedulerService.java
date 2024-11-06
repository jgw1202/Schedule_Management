package com.example.schedule_management.service;

import com.example.schedule_management.dto.SchedulerRequestDto;
import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchedulerService {
    SchedulerResponseDto saveScheduler(SchedulerRequestDto dto);

    List<SchedulerResponseDto> findAllSchedulers();

    SchedulerResponseDto findSchedulerById(Long id);

    SchedulerResponseDto updateScheduler(Long id, String password, String userName, String contents);

    void deleteScheduler(Long id, String password);

}
