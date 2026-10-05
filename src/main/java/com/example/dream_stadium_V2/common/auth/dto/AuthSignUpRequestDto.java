package com.example.dream_stadium_V2.common.auth.dto;

import com.example.dream_stadium_V2.common.user.entity.LoginType;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthSignUpRequestDto {

    @NotBlank(message = "비면 안됩니다.")
    private String email;

    @NotBlank(message = "비면 안됩니다.")
    private String password;

    @NotBlank(message = "비면 안됩니다.")
    private String nickname;

    @NotNull(message = "비면 안됩니다.")
    private UserRole userRole;

    @NotNull(message = "비면 안됩니다.")
    private LoginType loginType;

}
