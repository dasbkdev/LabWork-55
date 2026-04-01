package homework.lesson55.service;

import homework.lesson55.dao.OptionDao;
import homework.lesson55.dao.QuestionDao;
import homework.lesson55.dao.QuizDao;
import homework.lesson55.dao.QuizResultDao;
import homework.lesson55.dto.*;
import homework.lesson55.exception.BadRequestException;
import homework.lesson55.exception.NotFoundException;
import homework.lesson55.model.Option;
import homework.lesson55.model.Question;
import homework.lesson55.model.Quiz;
import homework.lesson55.model.QuizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuizService {

    private static final Logger log = LoggerFactory.getLogger(QuizService.class);

    private final QuizDao quizDao;
    private final QuestionDao questionDao;
    private final OptionDao optionDao;
    private final QuizResultDao quizResultDao;

    public QuizService(QuizDao quizDao, QuestionDao questionDao, OptionDao optionDao, QuizResultDao quizResultDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
        this.optionDao = optionDao;
        this.quizResultDao = quizResultDao;
    }

    public void createQuiz(CreateQuizRequestDto dto, String username) {
        validateQuiz(dto);

        Quiz quiz = new Quiz();
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setCreatorId(username);

        Long quizId = quizDao.create(quiz);

        for (QuestionCreateDto questionDto : dto.getQuestions()) {
            Question question = new Question();
            question.setQuizId(quizId);
            question.setQuestionText(questionDto.getQuestionText());

            Long questionId = questionDao.create(question);

            for (OptionCreateDto optionDto : questionDto.getOptions()) {
                Option option = new Option();
                option.setQuestionId(questionId);
                option.setOptionText(optionDto.getOptionText());
                option.setCorrect(optionDto.isCorrect());
                optionDao.create(option);
            }
        }

        log.info("Quiz created by user: {}", username);
    }

    public List<QuizResponseDto> getAllQuizzes() {
        List<Quiz> quizzes = quizDao.findAll();
        List<QuizResponseDto> result = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            result.add(new QuizResponseDto(
                    quiz.getId(),
                    quiz.getTitle(),
                    quiz.getDescription(),
                    quiz.getCreatorId()
            ));
        }

        return result;
    }

    public QuizDetailsResponseDto getQuizById(Long quizId) {
        Quiz quiz = quizDao.findById(quizId)
                .orElseThrow(() -> new NotFoundException("quiz not found"));

        List<Question> questions = questionDao.findByQuizId(quizId);
        List<QuestionResponseDto> questionDtos = new ArrayList<>();

        for (Question question : questions) {
            List<Option> options = optionDao.findByQuestionId(question.getId());
            List<OptionResponseDto> optionDtos = new ArrayList<>();

            for (Option option : options) {
                optionDtos.add(new OptionResponseDto(option.getId(), option.getOptionText()));
            }

            questionDtos.add(new QuestionResponseDto(
                    question.getId(),
                    question.getQuestionText(),
                    optionDtos
            ));
        }

        return new QuizDetailsResponseDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                questionDtos
        );
    }

    public QuizResultResponseDto solveQuiz(Long quizId, SolveQuizRequestDto dto, String username) {
        if (quizResultDao.existsByUsernameAndQuizId(username, quizId)) {
            throw new BadRequestException("quiz already solved");
        }

        Quiz quiz = quizDao.findById(quizId)
                .orElseThrow(() -> new NotFoundException("quiz not found"));

        List<Question> questions = questionDao.findByQuizId(quiz.getId());
        if (questions.isEmpty()) {
            throw new BadRequestException("quiz has no questions");
        }

        Map<Long, Long> correctAnswers = new HashMap<>();
        for (Question question : questions) {
            Option correctOption = optionDao.findCorrectByQuestionId(question.getId())
                    .orElseThrow(() -> new BadRequestException("question does not have correct option"));
            correctAnswers.put(question.getId(), correctOption.getId());
        }

        Map<Long, Long> userAnswers = new HashMap<>();
        for (AnswerDto answer : dto.getAnswers()) {
            if (userAnswers.containsKey(answer.getQuestionId())) {
                throw new BadRequestException("duplicate answer for question");
            }
            userAnswers.put(answer.getQuestionId(), answer.getSelectedOptionId());
        }

        if (userAnswers.size() != questions.size()) {
            throw new BadRequestException("all questions must be answered");
        }

        int correctCount = 0;
        for (Question question : questions) {
            Long selectedOptionId = userAnswers.get(question.getId());
            Long correctOptionId = correctAnswers.get(question.getId());

            if (selectedOptionId != null && selectedOptionId.equals(correctOptionId)) {
                correctCount++;
            }
        }

        int totalQuestions = questions.size();
        int wrongCount = totalQuestions - correctCount;
        int score = (correctCount * 100) / totalQuestions;

        QuizResult quizResult = new QuizResult();
        quizResult.setUsername(username);
        quizResult.setQuizId(quizId);
        quizResult.setCorrectAnswers(correctCount);
        quizResult.setWrongAnswers(wrongCount);
        quizResult.setTotalQuestions(totalQuestions);
        quizResult.setScore(score);
        quizResult.setUserRate(null);
        quizResult.setCompletedAt(LocalDateTime.now());

        quizResultDao.save(quizResult);

        log.info("Quiz solved by user: {}, quizId: {}", username, quizId);

        return new QuizResultResponseDto(
                quizId,
                correctCount,
                wrongCount,
                totalQuestions,
                score,
                null,
                quizResult.getCompletedAt()
        );
    }

    public QuizResultResponseDto getResults(Long quizId, String username) {
        QuizResult result = quizResultDao.findByUsernameAndQuizId(username, quizId)
                .orElseThrow(() -> new NotFoundException("result not found"));

        return new QuizResultResponseDto(
                result.getQuizId(),
                result.getCorrectAnswers(),
                result.getWrongAnswers(),
                result.getTotalQuestions(),
                result.getScore(),
                result.getUserRate(),
                result.getCompletedAt()
        );
    }

    public void rateQuiz(Long quizId, Integer rate, String username) {
        QuizResult result = quizResultDao.findByUsernameAndQuizId(username, quizId)
                .orElseThrow(() -> new BadRequestException("quiz must be solved before rating"));

        quizResultDao.updateRate(username, quizId, rate);
        log.info("Quiz rated by user: {}, quizId: {}, rate: {}", username, quizId, rate);
    }

    public List<LeaderboardEntryDto> getLeaderboard(Long quizId) {
        List<QuizResult> results = quizResultDao.findLeaderboardByQuizId(quizId);
        List<LeaderboardEntryDto> leaderboard = new ArrayList<>();

        for (QuizResult result : results) {
            leaderboard.add(new LeaderboardEntryDto(
                    result.getUsername(),
                    result.getScore(),
                    result.getCompletedAt()
            ));
        }

        return leaderboard;
    }

    public UserStatisticsDto getUserStatistics(String username) {
        int completed = Optional.ofNullable(quizResultDao.countCompletedByUsername(username)).orElse(0);
        double averageScore = Optional.ofNullable(quizResultDao.averageScoreByUsername(username)).orElse(0.0);

        return new UserStatisticsDto(username, completed, averageScore);
    }

    private void validateQuiz(CreateQuizRequestDto dto) {
        for (QuestionCreateDto question : dto.getQuestions()) {
            int correctCount = 0;
            for (OptionCreateDto option : question.getOptions()) {
                if (option.isCorrect()) {
                    correctCount++;
                }
            }

            if (correctCount != 1) {
                throw new BadRequestException("each question must have exactly one correct option");
            }
        }
    }
}