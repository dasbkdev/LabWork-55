package homework.lesson55.model;

public class Option {
    private Long id;
    private Long questionId;
    private String optionText;
    private boolean correct;

    public Option() {
    }

    public Option(Long id, Long questionId, String optionText, boolean correct) {
        this.id = id;
        this.questionId = questionId;
        this.optionText = optionText;
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

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