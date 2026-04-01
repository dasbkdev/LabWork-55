package homework.lesson55.dao;

import homework.lesson55.model.Quiz;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuizDao {

    private final JdbcTemplate jdbcTemplate;

    public QuizDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Quiz quiz) {
        String sql = "insert into quizzes(title, description, creator_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, quiz.getTitle(), quiz.getDescription(), quiz.getCreatorId());
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