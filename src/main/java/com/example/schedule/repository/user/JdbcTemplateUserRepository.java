package com.example.schedule.repository.user;

import com.example.schedule.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int registerUser(User user) {

        String query = "INSERT INTO user (uid, id, password, name, email, create_date, update_date) " +
                "VALUES (UUID(),?,?,?,?,CURRENT_DATE(),CURRENT_DATE())";

        return jdbcTemplate.update(query, user.getId(), user.getPassword(), user.getName(), user.getEmail());
    }
}
