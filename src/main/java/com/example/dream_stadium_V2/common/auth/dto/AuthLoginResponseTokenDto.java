package com.example.dream_stadium_V2.common.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponseTokenDto {

    private final Long userId;
    private final String accessToken;
    private final String refreshToken;
    private final boolean isDeleted;


    public AuthLoginResponseTokenDto(Long userId, String accessToken, String refreshToken, boolean isDeleted) {
        this.userId =  userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isDeleted = isDeleted;
    }

}
