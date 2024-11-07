package com.example.schedule.dto.schedule;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String user_uid;
    private int id;
    private String title;
    private String content;
    private String color;
    private String create_date;
    private String update_date;

    public ScheduleResponseDto(Schedule schedule) {
        this.user_uid = schedule.getUser_uid();
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.color = schedule.getColor();
        this.create_date = schedule.getCreate_date();
        this.update_date = schedule.getUpdate_date();
    }

    public ScheduleResponseDto(ScheduleRequestDto dto) {
        this.user_uid = dto.getUser_uid();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.color = dto.getColor();
    }
}
