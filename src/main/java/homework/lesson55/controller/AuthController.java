package homework.lesson55.controller;

import homework.lesson55.dto.RegisterRequestDto;
import homework.lesson55.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequestDto dto) {
        authService.register(dto);
        return ResponseEntity.ok(Map.of("message", "user registered successfully"));
    }
}