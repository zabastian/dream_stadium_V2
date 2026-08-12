package com.example.dream_stadium_V2.common.auth.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthLoginResponseDto {

    private final Long userId;
    private final String accessToken;
    private final String refreshToken;
    private final boolean isDeleted;

}
