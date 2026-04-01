package homework.lesson55.config;

import homework.lesson55.dto.CreateQuizRequestDto;
import homework.lesson55.dto.OptionCreateDto;
import homework.lesson55.dto.QuestionCreateDto;
import homework.lesson55.dto.RegisterRequestDto;
import homework.lesson55.service.AuthService;
import homework.lesson55.service.QuizService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthService authService;
    private final QuizService quizService;

    public DataInitializer(AuthService authService, QuizService quizService) {
        this.authService = authService;
        this.quizService = quizService;
    }

    @Override
    public void run(String... args) {
        RegisterRequestDto user = new RegisterRequestDto();
        user.setUsername("demo");
        user.setPassword("demo1234");

        try {
            authService.register(user);
        } catch (Exception ignored) {
        }

        CreateQuizRequestDto quiz = new CreateQuizRequestDto();
        quiz.setTitle("Java Basics");
        quiz.setDescription("Simple demo quiz");

        QuestionCreateDto q1 = new QuestionCreateDto();
        q1.setQuestionText("Java is?");
        OptionCreateDto q1o1 = new OptionCreateDto();
        q1o1.setOptionText("Programming language");
        q1o1.setCorrect(true);
        OptionCreateDto q1o2 = new OptionCreateDto();
        q1o2.setOptionText("Database");
        q1o2.setCorrect(false);
        q1.setOptions(List.of(q1o1, q1o2));

        QuestionCreateDto q2 = new QuestionCreateDto();
        q2.setQuestionText("Spring Boot is used for?");
        OptionCreateDto q2o1 = new OptionCreateDto();
        q2o1.setOptionText("Building Java applications");
        q2o1.setCorrect(true);
        OptionCreateDto q2o2 = new OptionCreateDto();
        q2o2.setOptionText("Editing images");
        q2o2.setCorrect(false);
        q2.setOptions(List.of(q2o1, q2o2));

        quiz.setQuestions(List.of(q1, q2));

        try {
            quizService.createQuiz(quiz, "demo");
        } catch (Exception ignored) {
        }
    }
}