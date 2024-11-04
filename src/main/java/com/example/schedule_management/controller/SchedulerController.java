package com.example.schedule_management.controller;

import com.example.schedule_management.dto.SchedulerRequestDto;
import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.service.SchedulerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedulers")
public class SchedulerController {

    private final SchedulerService schedulerService;

    // 일정 등록
    @PostMapping
    public ResponseEntity<SchedulerResponseDto> createScheduler(@RequestBody SchedulerRequestDto dto) {
        return new ResponseEntity<>(schedulerService.saveScheduler(dto), HttpStatus.CREATED);
    }

    // 일정 목록 조회
    @GetMapping
    public ResponseEntity<List<SchedulerResponseDto>> findAllScheduler() {
        return new ResponseEntity<>(schedulerService.findAllSchedulers(), HttpStatus.OK);
    }

    // 일정 조회
    @GetMapping("{id}")
    public ResponseEntity<SchedulerResponseDto> findSchedulerById(@PathVariable Long id) {
        return new ResponseEntity<>(schedulerService.findSchedulerById(id), HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("{id}")
    public ResponseEntity<SchedulerResponseDto> updateScheduler(@PathVariable Long id, @RequestBody SchedulerRequestDto dto) {
        return new ResponseEntity<>(schedulerService.updateScheduler(id, dto.getPassword(), dto.getUserName(), dto.getContents()), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteScheduler(@PathVariable Long id, @RequestBody SchedulerRequestDto dto) {
        schedulerService.deleteScheduler(id,dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
