package homework.lesson55.dto;

import java.util.List;

public class QuestionResponseDto {
    private Long id;
    private String questionText;
    private List<OptionResponseDto> options;

    public QuestionResponseDto() {
    }

    public QuestionResponseDto(Long id, String questionText, List<OptionResponseDto> options) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<OptionResponseDto> getOptions() {
        return options;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptions(List<OptionResponseDto> options) {
        this.options = options;
    }
}