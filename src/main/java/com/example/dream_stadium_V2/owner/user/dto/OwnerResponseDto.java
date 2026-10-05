package com.example.dream_stadium_V2.owner.user.dto;

import com.example.dream_stadium_V2.common.user.entity.LoginType;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import lombok.Getter;

@Getter
public class OwnerResponseDto {

    private final Long userId;
    private final boolean isDeleted;
    private final String nickname;
    private final UserRole userRole;
    private final LoginType loginType;

    public OwnerResponseDto(User user) {
        this.userId = user.getId();
        this.isDeleted = user.isDeleted();
        this.nickname = user.getNickname();
        this.userRole = user.getUserRole();
        this.loginType = user.getLoginType();
    }
}


