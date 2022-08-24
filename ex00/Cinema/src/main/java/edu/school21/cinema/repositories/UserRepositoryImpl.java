package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public User findById(Long id) {
        return template.queryForObject("SELECT * FROM users WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class),
                id);
    }

    public List<User> findAll() {
        return template.query("SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class));
    }

    public void save(User entity) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.withTableName("users").usingGeneratedKeyColumns("id");
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));
        entity.setId(id.longValue());
        System.err.println("[INFO] : user added with id = " + id.longValue());
    }

    public void delete(Long id) {
        template.update("DELETE FROM users WHERE id = ?", id);
    }

    public void update(User entity) {
        template.update("UPDATE users SET first_name = ? ," +
                " last_name = ? ," +
                " email = ? ," +
                " phone_number = ? ," +
                " password = ? WHERE id = ? ;",
                entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getPhoneNumber(), entity.getPassword(), entity.getId());
    }

    public User findByEmail(String email) {
        User user;
        try {
            user = template.queryForObject("SELECT * FROM users WHERE email = ?",
                    new BeanPropertyRowMapper<>(User.class),
                    email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }
}
