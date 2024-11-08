package com.example.schedule.dto.schedule;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String user_uid;
    private int id;
    private String user_name;
    private String title;
    private String content;
    private String color;
    private String create_date;
    private String update_date;

    public ScheduleResponseDto(ScheduleRequestDto dto) {
        this.user_uid = dto.getUser_uid();
        this.user_name = dto.getUser_name();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.color = dto.getColor();
    }

    public ScheduleResponseDto(Schedule dto) {
        this.user_uid = dto.getUser_uid();
        this.id= dto.getId();
        this.user_name = dto.getUser_name();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.color = dto.getColor();
        this.create_date = dto.getCreate_date();
        this.update_date = dto.getUpdate_date();
    }

}
