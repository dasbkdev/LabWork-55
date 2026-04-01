package homework.lesson55.dao;

import homework.lesson55.model.Quiz;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class QuizDao {

    private final JdbcTemplate jdbcTemplate;

    public QuizDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long create(Quiz quiz) {
        String sql = "insert into quizzes(title, description, creator_id) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, quiz.getTitle());
            preparedStatement.setString(2, quiz.getDescription());
            preparedStatement.setString(3, quiz.getCreatorId());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() == null) {
            throw new RuntimeException("Failed to create quiz");
        }

        return keyHolder.getKey().longValue();
    }

    public List<Quiz> findAll() {
        String sql = "select id, title, description, creator_id as creatorId from quizzes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Quiz.class));
    }

    public Optional<Quiz> findById(Long id) {
        String sql = "select id, title, description, creator_id as creatorId from quizzes where id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Quiz.class), id);

        if (quizzes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(quizzes.get(0));
    }
}