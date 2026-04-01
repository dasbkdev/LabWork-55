package homework.lesson55.dto;

import java.time.LocalDateTime;

public class QuizResultResponseDto {
    private Long quizId;
    private int correctAnswers;
    private int wrongAnswers;
    private int totalQuestions;
    private int score;
    private Integer userRate;
    private LocalDateTime completedAt;

    public QuizResultResponseDto() {
    }

    public QuizResultResponseDto(Long quizId, int correctAnswers, int wrongAnswers, int totalQuestions,
                                 int score, Integer userRate, LocalDateTime completedAt) {
        this.quizId = quizId;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.totalQuestions = totalQuestions;
        this.score = score;
        this.userRate = userRate;
        this.completedAt = completedAt;
    }

    public Long getQuizId() {
        return quizId;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public Integer getUserRate() {
        return userRate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUserRate(Integer userRate) {
        this.userRate = userRate;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}