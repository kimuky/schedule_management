package com.example.schedule.dto.schedule;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private String user_uid;
    private String user_name;
    private String title;
    private String content;
    private String color;
}
