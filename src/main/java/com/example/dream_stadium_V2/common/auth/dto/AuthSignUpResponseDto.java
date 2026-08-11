package com.example.dream_stadium_V2.common.auth.dto;

import lombok.Getter;
import org.hibernate.usertype.UserType;

@Getter
public class AuthSignUpResponseDto {
    private Long userId;
    private UserType userType;
}
