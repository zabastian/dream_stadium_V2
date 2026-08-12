package com.example.dream_stadium_V2.common.auth.service;

import com.example.dream_stadium_V2.common.auth.dto.AuthSignUpRequestDto;
import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class AuthService {

        private final AuthRepository authRepository;

        public void authSignUpService(AuthSignUpRequestDto dto) {
            User user = User.create(
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getNickname(),
                    dto.getUserRole(),
                    dto.getLoginType()
            );

            User savedUser = authRepository.save(user);
        }
}
