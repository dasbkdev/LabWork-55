package homework.lesson55.dao;

import homework.lesson55.model.Option;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OptionDao {

    private final JdbcTemplate jdbcTemplate;

    public OptionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Option option) {
        String sql = "insert into options(question_id, option_text, is_correct) values (?, ?, ?)";
        jdbcTemplate.update(sql, option.getQuestionId(), option.getOptionText(), option.isCorrect());
    }

    public List<Option> findByQuestionId(Long questionId) {
        String sql = "select id, question_id as questionId, option_text as optionText, is_correct as correct from options where question_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Option.class), questionId);
    }

    public Optional<Option> findCorrectByQuestionId(Long questionId) {
        String sql = "select id, question_id as questionId, option_text as optionText, is_correct as correct " +
                "from options where question_id = ? and is_correct = true";

        List<Option> options = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Option.class), questionId);
        if (options.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(options.get(0));
    }
}