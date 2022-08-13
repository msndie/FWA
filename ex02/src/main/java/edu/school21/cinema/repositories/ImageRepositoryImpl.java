package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public Image findById(Long id) {
        return template.queryForObject("SELECT * FROM images WHERE id = ?",
                new BeanPropertyRowMapper<>(Image.class),
                id);
    }

    public List<Image> findAll() {
        return template.query("SELECT * FROM images",
                new BeanPropertyRowMapper<>(Image.class));
    }

    public void save(Image entity) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.withTableName("images").usingGeneratedKeyColumns("id");
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));
        entity.setId(id.longValue());
        System.err.println("[INFO] : image added with id = " + id.longValue());
    }

    public void delete(Long id) {
        template.update("DELETE FROM images WHERE id = ?", id);
    }

    public void update(Image entity) {
        template.update("UPDATE images SET uuid = ? ," +
                        " name  = ? ," +
                        " mime = ? , size = ? WHERE id = ? ;",
                entity.getUuid(), entity.getName(), entity.getMime(), entity.getSize(), entity.getId());
    }

    public List<Image> findAllByUserId(Long id) {
        return template.query("SELECT id, user_id, uuid, name, mime, size FROM images WHERE user_id = ? ;",
                new BeanPropertyRowMapper<>(Image.class), id);
    }

    public Image findByUUID(UUID uuid) throws DataAccessException {
        return template.queryForObject("SELECT * FROM images WHERE uuid = ?",
                new BeanPropertyRowMapper<>(Image.class),
                uuid);
    }
}
