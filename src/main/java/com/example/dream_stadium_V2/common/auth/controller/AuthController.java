package com.example.dream_stadium_V2.common.auth.controller;

import com.example.dream_stadium_V2.common.auth.dto.AuthLoginRequestDto;
import com.example.dream_stadium_V2.common.auth.dto.AuthLoginResponseDto;
import com.example.dream_stadium_V2.common.auth.dto.AuthSignUpRequestDto;
import com.example.dream_stadium_V2.common.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUpController(@Valid @RequestBody AuthSignUpRequestDto authSignUpRequestDto) {
        authService.authSignUpService(authSignUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDto> loginController(@Valid @RequestBody AuthLoginRequestDto authLoginRequestDto) {
        AuthLoginResponseDto authLoginResponseDto = authService.authLoginService(authLoginRequestDto);
        return ResponseEntity.ok(authLoginResponseDto);
    }

}
// .requestMatchers("/auth/signUp", "/auth/login","/auth/refresh").permitAll()
