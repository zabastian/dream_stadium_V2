package com.example.dream_stadium_V2.owner.userCoupon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCouponResponseDto {

    private Long id;
    private Long userId;
    private Long couponId;
    private boolean isUsed;

    public UserCouponResponseDto(Long id, Long userId, Long couponId, boolean isUsed) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.isUsed = isUsed;
    }

}
