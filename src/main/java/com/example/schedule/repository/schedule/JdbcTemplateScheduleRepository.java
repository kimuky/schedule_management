package com.example.schedule.repository.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int createSchedule(ScheduleRequestDto dto) {

        String query = "INSERT INTO schedule (user_uid, user_name, title, content, color, create_date, update_date) " +
                "VALUES (?,?,?,?,?,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP())";

        return jdbcTemplate.update(query, dto.getUser_uid(), dto.getUser_name(), dto.getTitle(), dto.getContent(), dto.getColor());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("SELECT * FROM schedule ORDER BY update_date DESC, id", scheduleRowMapper());
    }

    @Override
    public Optional<Schedule> findScheduleById(int id) {
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public int updateSchedule(int id, String title, String content, String color) {
        return jdbcTemplate.update("UPDATE schedule SET title = ?, content = ?, color = ?, update_date = CURRENT_DATE() WHERE id = ?", title, content, color, id);
    }

    @Override
    public int updateScheduleTitle(int id, ScheduleRequestDto dto) {

        // 사용자가 제목, 내용 바꿀 수도 있고, 색깔만 바꿀 수도 있을 것이라 생각해 이렇게 구현했습니다.
        StringBuilder query = new StringBuilder("UPDATE schedule SET update_date = CURRENT_DATE()");
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

    @Override
    public int deleteScheduleTitle(int id) {
        return jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }

    @Override
    public List<ScheduleResponseDto> findPageSchedules(int pageNum, int pageSize) {
        return jdbcTemplate.query("SELECT * FROM schedule ORDER BY update_date DESC, id LIMIT ?,?", scheduleRowMapper(), (pageNum - 1) * pageSize, pageSize);

    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new ScheduleResponseDto(
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

    private RowMapper<Schedule> scheduleRowMapperV2() {
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
