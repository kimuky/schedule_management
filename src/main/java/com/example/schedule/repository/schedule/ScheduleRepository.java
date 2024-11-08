package com.example.schedule.repository.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    int createSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    Optional<Schedule> findScheduleById(int id);

    int updateSchedule(int id, String title, String content, String color);

    int updateScheduleTitle(int id, ScheduleRequestDto dto);

    int deleteSchedule(int id);

    List<ScheduleResponseDto> findPageSchedules(int pageNum, int pageSize);
}
