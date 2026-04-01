package homework.lesson55.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.List;

public class SolveQuizRequestDto {

    @Valid
    @Size(min = 1)
    private List<AnswerDto> answers;

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}