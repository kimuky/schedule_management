package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

    private String user_uid;
    private int id;
    private String user_name;
    private String title;
    private String content;
    private String color;
    private String create_date;
    private String update_date;
}
