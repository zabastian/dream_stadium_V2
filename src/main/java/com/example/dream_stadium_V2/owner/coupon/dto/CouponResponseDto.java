package com.example.dream_stadium_V2.owner.coupon.dto;

import lombok.Getter;

@Getter
public class CouponResponseDto {
    private Long id;
    private String name;
    private Long discountRate;


    public CouponResponseDto(Long id, String name, Long discountRate) {
        this.id = id;
        this.name = name;
        this.discountRate = discountRate;
    }
}
