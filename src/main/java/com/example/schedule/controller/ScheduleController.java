package com.example.schedule.controller;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.service.schedule.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 스케쥴 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.OK);
    }

    /**
     * 페이지네이션
     *
     * @param pageNum  페이지 번호
     * @param pageSize 페이지 사이즈
     * @return 페이지 번호와 페이지 사이즈에 맞는 스케쥴 배열
     */
    @GetMapping("/param")
    public List<ScheduleResponseDto> pagination(@RequestParam int pageNum, @RequestParam int pageSize) {
        return scheduleService.pagination(pageNum, pageSize);
    }

    // 전체 스케쥴 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    // 선택 스케쥴 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable int id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    /**
     * 전체 수정
     *
     * @param id  해당 스케쥴의 아이디
     * @param dto 제목, 내용, 색깔
     * @return 업데이트 후 스케쥴
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getUser_uid(), dto.getTitle(), dto.getContent(), dto.getColor()), HttpStatus.OK);
    }

    /**
     * 부분 수정
     * - Putmapping 과 같은 역할을 하지만 사용자가 주입하는 JSON 에 따라 업데이트가 달라지게 구현
     *
     * @param id  해당 스케쥴의 아이디
     * @param dto 제목 내용 수정
     * @return 스케쥴 반환
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleTitle(@PathVariable int id, @RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedulePart(id, dto), HttpStatus.OK);
    }

    // 스케쥴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto dto) {
        scheduleService.deleteSchedule(id, dto.getUser_uid());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
