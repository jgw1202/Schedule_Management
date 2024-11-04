package com.example.schedule_management.repository;

import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcTemplateSchedulerRepository implements  SchedulerRepository {

   private final Map<Long, Scheduler> schedulerList = new HashMap<>();
    @Override
    public Scheduler saveScheduler(Scheduler scheduler) {

        Long schedulerId = schedulerList.isEmpty() ? 1 : Collections.max(schedulerList.keySet()) + 1;
        scheduler.setId(schedulerId);

        schedulerList.put(schedulerId, scheduler);

        return scheduler;

    }

    @Override
    public List<SchedulerResponseDto> findAllSchedulers() {

        List<SchedulerResponseDto> allSchedulers = new ArrayList<>();

        for(Scheduler scheduler : schedulerList.values()) {
            SchedulerResponseDto responseDto = new SchedulerResponseDto(scheduler);
            allSchedulers.add(responseDto);
        }

        return allSchedulers;
    }

    @Override
    public Scheduler findSchedulerById(Long id) {
        return schedulerList.get(id);
    }

    @Override
    public void deleteScheduler(Long id) {
        schedulerList.remove(id);
    }
}
