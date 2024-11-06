package com.example.schedule.repository.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public int updateSchedule(int id, String title, String content, String color) {
        return jdbcTemplate.update("update schedule set title = ?, content = ?, color = ?, update_date = CURRENT_DATE() where id = ?", title, content, color, id);
    }

    @Override
    public int updateScheduleTitle(int id, ScheduleRequestDto dto) {
        // 이렇게 말고 다른 방법은 아예 모르겠습니다....
        StringBuilder query = new StringBuilder("UPDATE schedule set update_date = CURRENT_DATE()");
        List<Object> params = new ArrayList<>();

        if (dto.getTitle() != null) {
            query.append(", title = ?");
            params.add(dto.getTitle());
        }
        if (dto.getContent() != null) {
            query.append(", content = ?");
            params.add(dto.getContent());
        }
        if (dto.getColor() != null) {
            query.append(", color = ?");
            params.add(dto.getColor());
        }

        query.append(" WHERE id = ?");
        params.add(id);


        return jdbcTemplate.update(query.toString(), params.toArray());
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
