package com.example.dream_stadium_V2.owner.coupon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CouponRequestDto {

    @NotBlank(message = "비면 안됩니다.")
    private String name;

    @NotNull(message = "비면 안됩니다.")
    private Long discountRate;
}
