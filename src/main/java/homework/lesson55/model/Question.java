package homework.lesson55.model;

public class Question {
    private Long id;
    private Long quizId;
    private String questionText;

    public Question() {
    }

    public Question(Long id, Long quizId, String questionText) {
        this.id = id;
        this.quizId = quizId;
        this.questionText = questionText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}