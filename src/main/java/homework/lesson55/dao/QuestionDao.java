package homework.lesson55.dao;

import homework.lesson55.model.Question;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class QuestionDao {

    private final JdbcTemplate jdbcTemplate;

    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long create(Question question) {
        String sql = "insert into questions(quiz_id, question_text) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, question.getQuizId());
            ps.setString(2, question.getQuestionText());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<Question> findByQuizId(Long quizId) {
        String sql = "select id, quiz_id as quizId, question_text as questionText from questions where quiz_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Question.class), quizId);
    }
}