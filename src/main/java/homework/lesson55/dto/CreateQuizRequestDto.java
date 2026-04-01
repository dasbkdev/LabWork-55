package homework.lesson55.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CreateQuizRequestDto {

    @NotBlank
    private String title;

    private String description;

    @Valid
    @Size(min = 1)
    private List<QuestionCreateDto> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionCreateDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionCreateDto> questions) {
        this.questions = questions;
    }
}