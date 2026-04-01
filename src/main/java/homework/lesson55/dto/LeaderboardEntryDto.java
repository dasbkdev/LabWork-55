package homework.lesson55.dto;

import java.time.LocalDateTime;

public class LeaderboardEntryDto {
    private String username;
    private int score;
    private LocalDateTime completedAt;

    public LeaderboardEntryDto() {
    }

    public LeaderboardEntryDto(String username, int score, LocalDateTime completedAt) {
        this.username = username;
        this.score = score;
        this.completedAt = completedAt;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}