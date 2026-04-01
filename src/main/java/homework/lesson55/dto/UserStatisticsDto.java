package homework.lesson55.dto;

public class UserStatisticsDto {
    private String username;
    private int completedQuizzes;
    private double averageScore;

    public UserStatisticsDto() {
    }

    public UserStatisticsDto(String username, int completedQuizzes, double averageScore) {
        this.username = username;
        this.completedQuizzes = completedQuizzes;
        this.averageScore = averageScore;
    }

    public String getUsername() {
        return username;
    }

    public int getCompletedQuizzes() {
        return completedQuizzes;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCompletedQuizzes(int completedQuizzes) {
        this.completedQuizzes = completedQuizzes;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}