package com.example.dream_stadium_V2.common.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginRequestDto {

    private String email;

    private String password;

    private String recaptchaToken;
}
