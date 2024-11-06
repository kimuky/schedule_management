package com.example.schedule.repository.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int createSchedule(ScheduleRequestDto dto) {

        String query = "INSERT INTO schedule (user_uid, title, content, color, create_date, update_date) " +
                "VALUES (?,?,?,?,CURRENT_DATE(),CURRENT_DATE())";

        return jdbcTemplate.update(query, dto.getUser_uid(), dto.getTitle(), dto.getContent(), dto.getColor());
    }
}
