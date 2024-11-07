package com.example.schedule.service.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(int id);

    ScheduleResponseDto updateSchedule(int id, String userUid, String title, String content, String color);

    ScheduleResponseDto updateSchedulePart(int id, ScheduleRequestDto dto);

    void deleteSchedule(int id, String userUid);

    List<ScheduleResponseDto> pagination(int pageNum, int pageSize);
}
