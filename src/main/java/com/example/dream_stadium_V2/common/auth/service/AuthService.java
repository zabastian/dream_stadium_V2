package com.example.dream_stadium_V2.common.auth.service;

import com.example.dream_stadium_V2.common.auth.dto.AuthLoginRequestDto;
import com.example.dream_stadium_V2.common.auth.dto.AuthLoginResponseDto;
import com.example.dream_stadium_V2.common.auth.dto.AuthSignUpRequestDto;
import com.example.dream_stadium_V2.common.auth.recaptcha.RecaptchaService;
import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.LoginType;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.global.spring_security.TokenResponse;
import com.example.dream_stadium_V2.global.spring_security.TokenService;
import com.example.dream_stadium_V2.global.spring_security.refresh_token.RefreshToken;
import com.example.dream_stadium_V2.global.spring_security.refresh_token.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
    @RequiredArgsConstructor
    public class AuthService {

        private final AuthRepository authRepository;
        private final PasswordEncoder passwordEncoder;
        private final TokenService tokenService;
        private final RefreshTokenRepository refreshTokenRepository;
    private final RecaptchaService recaptchaService;

    public void authSignUpService(AuthSignUpRequestDto dto) {
            User user = User.create(
                    dto.getEmail(),
                    passwordEncoder.encode(dto.getPassword()),
                    dto.getNickname(),
                    dto.getUserRole(),
                    dto.getLoginType()
            );

            User savedUser = authRepository.save(user);
        }

        public AuthLoginResponseDto authLoginService(AuthLoginRequestDto dto) {

            User user = authRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

            if (user.getLoginType() == LoginType.LOCAL) {
                if (!recaptchaService.verify(dto.getRecaptchaToken())) {
                    throw new BaseException(ErrorCode.RECAPTCHA_FAILED);
                }

                if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                    throw new BaseException(ErrorCode.LOGIN_FAILED);
                }
            }


            String accessToken = tokenService.createAccessToken(user.getId(), user.getUserRole());
            String refreshToken = tokenService.createRefreshToken();

            if (refreshTokenRepository.findByUserId(user.getId()).isPresent() && !refreshTokenRepository.findByUserId(user.getId()).get().isExpired()) {
                refreshToken = refreshTokenRepository.findByUserId(user.getId()).get().getToken();
            }
            else {
                RefreshToken refreshTokenNew = new RefreshToken(refreshToken, user.getId(), user.getUserRole(), Instant.now().plus(30, ChronoUnit.DAYS));

                refreshTokenRepository.save(refreshTokenNew);
            }

            return new AuthLoginResponseDto(user.getId(), accessToken, refreshToken, user.isDeleted());

        }
}
