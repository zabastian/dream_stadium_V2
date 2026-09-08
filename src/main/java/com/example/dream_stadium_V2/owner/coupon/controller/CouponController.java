package com.example.dream_stadium_V2.owner.coupon.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.coupon.dto.CouponRequestDto;
import com.example.dream_stadium_V2.owner.coupon.dto.CouponResponseDto;
import com.example.dream_stadium_V2.owner.coupon.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/coupon") // 쿠폰 생성
    public ResponseEntity<CouponResponseDto> createdCoupon(
            @Valid @RequestBody CouponRequestDto couponRequestDto,
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
            ) {
        CouponResponseDto couponResponseDto = couponService.createCoupon(couponRequestDto, customUserPrincipal);
        return ResponseEntity.ok().body(couponResponseDto);
    }

    @GetMapping("/coupon/list") // 해당 owner에 해당하는 couponlist를 조회
    public ResponseEntity<List<CouponResponseDto>> selectedListCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
            ) {
        List<CouponResponseDto> couponResponseDto = couponService.selectListCoupon(customUserPrincipal);
        return ResponseEntity.ok().body(couponResponseDto);
    }

    @PostMapping("/coupon/update/{couponId}")
    public ResponseEntity<CouponResponseDto> updatedCoupon(
            @Valid @RequestBody CouponRequestDto couponRequestDto,
            @PathVariable Long couponId
    ) {
        CouponResponseDto couponResponseDto = couponService.updateCoupon(couponRequestDto, couponId);
        return ResponseEntity.ok().body(couponResponseDto);
    }

    @DeleteMapping("/coupon/delete/{couponId}")
    public ResponseEntity<Void> deletedCoupon(
            @PathVariable Long couponId
    ) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.noContent().build();
    }



}
