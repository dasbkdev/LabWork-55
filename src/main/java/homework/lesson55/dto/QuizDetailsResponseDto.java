package homework.lesson55.dto;

import java.util.List;

public class QuizDetailsResponseDto {
    private Long id;
    private String title;
    private String description;
    private List<QuestionResponseDto> questions;

    public QuizDetailsResponseDto() {
    }

    public QuizDetailsResponseDto(Long id, String title, String description, List<QuestionResponseDto> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<QuestionResponseDto> getQuestions() {
        return questions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(List<QuestionResponseDto> questions) {
        this.questions = questions;
    }
}