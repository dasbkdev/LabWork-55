package homework.lesson55.dto;

import jakarta.validation.constraints.NotNull;

public class AnswerDto {

    @NotNull
    private Long questionId;

    @NotNull
    private Long selectedOptionId;

    public Long getQuestionId() {
        return questionId;
    }

    public Long getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setSelectedOptionId(Long selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }
}