package com.example.dream_stadium_V2.common.auth.oauth;

import com.example.dream_stadium_V2.common.auth.cookie.CookieUtil;
import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.spring_security.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler
        implements AuthenticationSuccessHandler {

    private final AuthRepository authRepository;
    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        Map<String, Object> responseData =
                (Map<String, Object>) oauth2User
                        .getAttributes()
                        .get("response");

        String email = (String) responseData.get("email");

        // DB에서 유저 조회
        User user = authRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "네이버 로그인 사용자를 찾을 수 없습니다."
                        )
                );

        // JWT 발급
        String accessToken =
                tokenService.createAccessToken(
                        user.getId(),
                        user.getUserRole()
                );


        // Cookie 생성
        Cookie cookie = CookieUtil.createCookie(accessToken);

        response.addCookie(cookie);

        // 로그인 완료 후 메인 페이지 이동
        response.sendRedirect("/");
    }
}