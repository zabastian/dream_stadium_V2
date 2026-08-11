package com.example.dream_stadium_V2.global.spring_security;

import lombok.Getter;

@Getter
public class CustomUserPrincipal {
    private final Long userId;
    private final String role;

    public CustomUserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}