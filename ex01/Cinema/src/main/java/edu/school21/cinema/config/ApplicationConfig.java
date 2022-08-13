package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:../application.properties")
public class ApplicationConfig {

    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String pass;
    @Value("${db.driver.name}")
    private String driver;

    @Bean
    public JdbcTemplate getJdbcTemplate() throws IOException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);
        ds.setDriverClassName(driver);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        String schema = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/schema.sql")), StandardCharsets.UTF_8);
        String data = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/data.sql")), StandardCharsets.UTF_8);
        jdbcTemplate.execute(schema);
        jdbcTemplate.execute(data);
        return jdbcTemplate;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
