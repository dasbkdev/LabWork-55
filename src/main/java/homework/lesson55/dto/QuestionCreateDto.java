package homework.lesson55.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class QuestionCreateDto {

    @NotBlank
    private String questionText;

    @Valid
    @Size(min = 2)
    private List<OptionCreateDto> options;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<OptionCreateDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionCreateDto> options) {
        this.options = options;
    }
}