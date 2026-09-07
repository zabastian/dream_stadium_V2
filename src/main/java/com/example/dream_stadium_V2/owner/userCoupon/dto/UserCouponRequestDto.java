package com.example.dream_stadium_V2.owner.userCoupon.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCouponRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long couponId;

    @NotBlank
    private String name;

    private boolean isUsed;
}
