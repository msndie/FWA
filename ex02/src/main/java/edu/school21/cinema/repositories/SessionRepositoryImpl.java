package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public Session findById(Long id) {
        return template.queryForObject("SELECT * FROM sessions WHERE id = ?",
                new BeanPropertyRowMapper<>(Session.class),
                id);
    }

    public List<Session> findAll() {
        return template.query("SELECT * FROM sessions",
                new BeanPropertyRowMapper<>(Session.class));
    }

    public void save(Session entity) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.withTableName("sessions").usingGeneratedKeyColumns("id");
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));
        entity.setId(id.longValue());
        System.err.println("[INFO] : session added with id = " + id.longValue());
    }

    public void delete(Long id) {
        template.update("DELETE FROM sessions WHERE id = ?", id);
    }

    public void update(Session entity) {
        template.update("UPDATE sessions SET date_time = ? ," +
                        " ip = ? ," +
                        " user_id = ? WHERE id = ? ;",
                entity.getDate_time(), entity.getIp(), entity.getUser_id(), entity.getId());
    }

    public List<Session> findAllByUserId(Long id) {
        return template.query("SELECT id, user_id, date_time, ip FROM sessions WHERE user_id = ? ;",
                new BeanPropertyRowMapper<>(Session.class), id);
    }
}
