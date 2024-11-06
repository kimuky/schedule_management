package com.example.schedule.dto.schedule;

import com.example.schedule.entity.Schedule;

public class ScheduleResponseDto {
    private String user_uid;
    private String title;
    private String content;
    private String color;

    public ScheduleResponseDto(String user_uid){
        this.user_uid = user_uid;
    }
}
