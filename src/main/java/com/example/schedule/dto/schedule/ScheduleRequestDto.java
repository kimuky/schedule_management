package com.example.schedule.dto.schedule;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "유저 식별번호가 없습니다.")
    private String user_uid;

    @NotBlank(message = "유저 이름이 없습니다.")
    private String user_name;

    @NotBlank(message = "할일은 적어주세요.")
    @Size(max = 200, message = "최대 200글자입니다.")
    private String title;
    private String content;
    private String color;
}
