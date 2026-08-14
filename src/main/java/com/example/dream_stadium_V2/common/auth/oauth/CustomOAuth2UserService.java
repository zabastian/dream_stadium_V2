package com.example.dream_stadium_V2.common.auth.oauth;


import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.LoginType;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AuthRepository authRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);

        Map<String, Object> response =
                (Map<String, Object>) oauth2User.getAttributes().get("response");

        String email = (String) response.get("email");
        String nickname = (String) response.get("nickname");

        // 기존 회원인지 확인
        User user = authRepository.findByEmail(email)
                .orElseGet(() -> {

                    // 신규 회원이면 회원가입
                    User newUser = User.createNaverUser(email, nickname, UserRole.CUSTOMER, LoginType.NAVER);

                    return authRepository.save(newUser);
                });

        return oauth2User;
    }
}