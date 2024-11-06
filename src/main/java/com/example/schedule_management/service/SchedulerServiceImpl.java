package com.example.schedule_management.service;

import com.example.schedule_management.dto.SchedulerRequestDto;
import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.entity.Scheduler;
import com.example.schedule_management.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Override
    public SchedulerResponseDto saveScheduler(SchedulerRequestDto dto) {
        Scheduler scheduler = new Scheduler(dto.getPassword(), dto.getUserName(), dto.getContents());
        return schedulerRepository.saveScheduler(scheduler);
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedulers(int page, int size) {
        return schedulerRepository.findAllSchedulers(page, size);
    }

    @Override
    public SchedulerResponseDto findSchedulerById(Long id) {
        Optional<Scheduler> scheduler = schedulerRepository.findSchedulerById(id);
        if (scheduler.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheduler not found with id = " + id);
        }
        Scheduler foundScheduler = scheduler.get();
        return new SchedulerResponseDto(
                foundScheduler.getId(),
                foundScheduler.getUserName(),
                foundScheduler.getContents(),
                foundScheduler.getCreatedAt(),
                foundScheduler.getUpdatedAt()
        );
    }

    @Transactional
    @Override
    public SchedulerResponseDto updateScheduler(Long id, String password, String userName, String contents) {
        if (userName == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        Optional<Scheduler> existingScheduler = schedulerRepository.findSchedulerById(id);
        if (existingScheduler.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheduler not found with id = " + id);
        }
        if (!existingScheduler.get().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match.");
        }

        int updatedRow = schedulerRepository.updateScheduler(id, userName, contents);
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new SchedulerResponseDto(
                existingScheduler.get().getId(),
                userName,
                contents,
                existingScheduler.get().getCreatedAt(),
                new Date()  // updatedAt 갱신
        );
    }

    @Override
    public void deleteScheduler(Long id, String password) {
        Optional<Scheduler> existingScheduler = schedulerRepository.findSchedulerById(id);
        if (existingScheduler.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheduler not found with id = " + id);
        }
        if (!existingScheduler.get().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match.");
        }

        int deleteRow = schedulerRepository.deleteScheduler(id);
        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheduler not found with id = " + id);
        }
    }

    @Override
    public int countAllSchedulers() {
        return schedulerRepository.countAllSchedulers();
    }
}

