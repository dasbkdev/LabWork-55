package homework.lesson55.dto;

import jakarta.validation.constraints.NotBlank;

public class OptionCreateDto {

    @NotBlank
    private String optionText;

    private boolean correct;

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}