package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repositories.SessionRepository;
import edu.school21.cinema.repositories.SessionRepositoryImpl;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.services.SessionServiceImpl;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:../application.properties")
//@PropertySource("file:src/main/webapp/WEB-INF/application.properties")
public class ApplicationConfig {

    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String pass;
    @Value("${db.driver.name}")
    private String driver;
    @Value("${storage.path}")
    private String storage;

    @Bean
    public String getStoragePath() {
        return storage;
    }

    @Bean
    public HikariDataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);
        ds.setDriverClassName(driver);
        return ds;
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

    @Bean
    public SessionService getSessionService() {
        return new SessionServiceImpl();
    }

    @Bean
    public SessionRepository getSessionRepository() {
        return new SessionRepositoryImpl();
    }
}
