package com.example.schedule_management.service;

import com.example.schedule_management.dto.SchedulerRequestDto;
import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;
import com.example.schedule_management.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements  SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Override
    public SchedulerResponseDto saveScheduler(SchedulerRequestDto dto) {

        Scheduler scheduler = new Scheduler(dto.getPassword(), dto.getUserName(), dto.getContents());

        Scheduler savedScheduler = schedulerRepository.saveScheduler(scheduler);

        return new SchedulerResponseDto(savedScheduler);
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedulers() {

        List<SchedulerResponseDto> allSchedulers = schedulerRepository.findAllSchedulers();

        return allSchedulers;
    }

    @Override
    public SchedulerResponseDto findSchedulerById(Long id) {

        Scheduler scheduler = schedulerRepository.findSchedulerById(id);
        // NPE 방지
        if (scheduler == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new SchedulerResponseDto(scheduler);
    }

    @Override
    public SchedulerResponseDto updateScheduler(Long id, String password, String userName, String contents) {

        Scheduler scheduler = schedulerRepository.findSchedulerById(id);

        // NPE 방지
        if (scheduler == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 필수값 검증
        if (password == null || userName == null|| contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password, userName and contents are required values.");
        }

        scheduler.update(password, userName, contents);

        return new SchedulerResponseDto(scheduler);

    }


}
