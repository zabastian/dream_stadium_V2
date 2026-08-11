package com.example.dream_stadium_V2.common.auth.controller;

import com.example.dream_stadium_V2.common.auth.dto.AuthSignUpRequestDto;
import com.example.dream_stadium_V2.common.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
        System.out.println("SIGNUP CONTROLLER");
        authService.authSignUpService(authSignUpRequestDto);
        return ResponseEntity.ok().build();
    }

}
// .requestMatchers("/auth/signUp", "/auth/login","/auth/refresh").permitAll()
