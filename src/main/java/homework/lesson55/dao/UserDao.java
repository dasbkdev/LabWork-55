package homework.lesson55.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsByUsername(String username) {
        String sql = "select count(*) from users where username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public void createUser(String username, String password, boolean enabled) {
        String sql = "insert into users(username, password, enabled) values (?, ?, ?)";
        jdbcTemplate.update(sql, username, password, enabled);
    }

    public void addAuthority(String username, String authority) {
        String sql = "insert into authorities(username, authority) values (?, ?)";
        jdbcTemplate.update(sql, username, authority);
    }
}