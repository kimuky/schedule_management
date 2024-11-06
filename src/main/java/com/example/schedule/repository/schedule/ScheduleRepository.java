package com.example.schedule.repository.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;

public interface ScheduleRepository {
    int createSchedule(ScheduleRequestDto dto);
}
