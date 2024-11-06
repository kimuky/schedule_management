package com.example.schedule.repository.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("SELECT * FROM schedule order by update_date desc, id", scheduleRowMapper());
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(int id) {
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule where id = ?", scheduleRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 없음"));
    }

    //TODO 전체 다 반환? 고민해볼것
    private RowMapper<ScheduleResponseDto> scheduleRowMapper () {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return  new ScheduleResponseDto(
                        rs.getString("user_uid"),
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("color"),
                        rs.getString("create_date"),
                        rs.getString("update_date")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2 () {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getString("user_uid"),
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("color"),
                        rs.getString("create_date"),
                        rs.getString("update_date")
                );
            }
        };

    }
}
