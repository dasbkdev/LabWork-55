package homework.lesson55.controller;

import homework.lesson55.dto.*;
import homework.lesson55.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quizzes")
    public ResponseEntity<Map<String, String>> createQuiz(@Valid @RequestBody CreateQuizRequestDto dto,
                                                          Authentication authentication) {
        quizService.createQuiz(dto, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "quiz created successfully"));
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<QuizResponseDto>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<QuizDetailsResponseDto> getQuizById(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    @PostMapping("/quizzes/{quizId}/solve")
    public ResponseEntity<QuizResultResponseDto> solveQuiz(@PathVariable Long quizId,
                                                           @Valid @RequestBody SolveQuizRequestDto dto,
                                                           Authentication authentication) {
        return ResponseEntity.ok(quizService.solveQuiz(quizId, dto, authentication.getName()));
    }

    @GetMapping("/quizzes/{quizId}/results")
    public ResponseEntity<QuizResultResponseDto> getResults(@PathVariable Long quizId,
                                                            Authentication authentication) {
        return ResponseEntity.ok(quizService.getResults(quizId, authentication.getName()));
    }

    @PostMapping("/quizzes/{quizId}/rate")
    public ResponseEntity<Map<String, String>> rateQuiz(@PathVariable Long quizId,
                                                        @Valid @RequestBody RateQuizRequestDto dto,
                                                        Authentication authentication) {
        quizService.rateQuiz(quizId, dto.getRate(), authentication.getName());
        return ResponseEntity.ok(Map.of("message", "quiz rated successfully"));
    }

    @GetMapping("/quizzes/{quizId}/leaderboard")
    public ResponseEntity<List<LeaderboardEntryDto>> getLeaderboard(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getLeaderboard(quizId));
    }

    @GetMapping("/users/{userId}/statistics")
    public ResponseEntity<UserStatisticsDto> getUserStatistics(@PathVariable String userId) {
        return ResponseEntity.ok(quizService.getUserStatistics(userId));
    }
}