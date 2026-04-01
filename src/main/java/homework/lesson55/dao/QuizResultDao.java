package homework.lesson55.dao;

import homework.lesson55.model.QuizResult;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuizResultDao {

    private final JdbcTemplate jdbcTemplate;

    public QuizResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(QuizResult quizResult) {
        String sql = "insert into quiz_results(username, quiz_id, correct_answers, wrong_answers, total_questions, score, user_rate, completed_at) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                quizResult.getUsername(),
                quizResult.getQuizId(),
                quizResult.getCorrectAnswers(),
                quizResult.getWrongAnswers(),
                quizResult.getTotalQuestions(),
                quizResult.getScore(),
                quizResult.getUserRate(),
                quizResult.getCompletedAt());
    }

    public boolean existsByUsernameAndQuizId(String username, Long quizId) {
        String sql = "select count(*) from quiz_results where username = ? and quiz_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username, quizId);
        return count != null && count > 0;
    }

    public Optional<QuizResult> findByUsernameAndQuizId(String username, Long quizId) {
        String sql = "select id, username, quiz_id as quizId, correct_answers as correctAnswers, wrong_answers as wrongAnswers, " +
                "total_questions as totalQuestions, score, user_rate as userRate, completed_at as completedAt " +
                "from quiz_results where username = ? and quiz_id = ?";

        List<QuizResult> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QuizResult.class), username, quizId);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(0));
    }

    public void updateRate(String username, Long quizId, Integer rate) {
        String sql = "update quiz_results set user_rate = ? where username = ? and quiz_id = ?";
        jdbcTemplate.update(sql, rate, username, quizId);
    }

    public List<QuizResult> findLeaderboardByQuizId(Long quizId) {
        String sql = "select id, username, quiz_id as quizId, correct_answers as correctAnswers, wrong_answers as wrongAnswers, " +
                "total_questions as totalQuestions, score, user_rate as userRate, completed_at as completedAt " +
                "from quiz_results where quiz_id = ? order by score desc, completed_at asc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QuizResult.class), quizId);
    }

    public Integer countCompletedByUsername(String username) {
        String sql = "select count(*) from quiz_results where username = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

    public Double averageScoreByUsername(String username) {
        String sql = "select avg(score) from quiz_results where username = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, username);
    }
}