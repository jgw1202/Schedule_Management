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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements  SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Override
    public SchedulerResponseDto saveScheduler(SchedulerRequestDto dto) {

        Scheduler scheduler = new Scheduler(dto.getPassword(), dto.getUserName(), dto.getContents());

        return schedulerRepository.saveScheduler(scheduler);
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedulers() {

        List<SchedulerResponseDto> allSchedulers = schedulerRepository.findAllSchedulers();

        return allSchedulers;
    }

    @Override
    public SchedulerResponseDto findSchedulerById(Long id) {

        Optional<Scheduler> scheduler = schedulerRepository.findSchedulerById(id);
        // NPE 방지
        if (scheduler == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new SchedulerResponseDto(scheduler.get());
    }

    @Transactional
    @Override
    public SchedulerResponseDto updateScheduler(Long id, String password, String userName, String contents) {

        // 필수값 검증
        if (userName == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // memo 수정
        int updatedRow = schedulerRepository.updateScheduler(id, userName, contents);
        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        // 수정된 메모 조회
        return new SchedulerResponseDto(schedulerRepository.findSchedulerById(id).get());

    }

    @Override
    public void deleteScheduler(Long id, String password) {

        int deleteRow = schedulerRepository.deleteScheduler(id);
        // NPE 방지
        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

    }


}
