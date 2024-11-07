package com.example.schedule_management.controller;

import com.example.schedule_management.dto.SchedulerRequestDto;
import com.example.schedule_management.dto.SchedulerResponseDto;
import com.example.schedule_management.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedulers")
public class SchedulerController {
    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping
    public ResponseEntity<SchedulerResponseDto> createScheduler(@RequestBody SchedulerRequestDto schedulerRequestDto) {
        SchedulerResponseDto createdScheduler = schedulerService.saveScheduler(schedulerRequestDto);
        return new ResponseEntity<>(createdScheduler, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SchedulerResponseDto>> getAllSchedulers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<SchedulerResponseDto> schedulers = schedulerService.findAllSchedulers(page, size);
        return new ResponseEntity<>(schedulers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getSchedulerCount() {
        int count = schedulerService.countAllSchedulers();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> getSchedulerById(@PathVariable Long id) {
        SchedulerResponseDto scheduler = schedulerService.findSchedulerById(id);
        return new ResponseEntity<>(scheduler, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> updateScheduler(@PathVariable Long id, @RequestBody SchedulerRequestDto schedulerRequestDto) {
        SchedulerResponseDto updatedScheduler = schedulerService.updateScheduler(id, schedulerRequestDto.getPassword(), schedulerRequestDto.getUserName(), schedulerRequestDto.getContents());
        return new ResponseEntity<>(updatedScheduler, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduler(@PathVariable Long id, @RequestParam String password) {
        schedulerService.deleteScheduler(id, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

