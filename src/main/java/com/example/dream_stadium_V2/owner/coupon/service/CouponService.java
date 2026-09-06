package com.example.dream_stadium_V2.owner.coupon.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.coupon.dto.CouponRequestDto;
import com.example.dream_stadium_V2.owner.coupon.dto.CouponResponseDto;
import com.example.dream_stadium_V2.owner.coupon.entity.Coupon;
import com.example.dream_stadium_V2.owner.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final AuthRepository authRepository;

    public CouponResponseDto createCoupon(CouponRequestDto dto) {

        Coupon coupon  = Coupon.create(dto.getName(), dto.getDiscountRate());

        couponRepository.save(coupon);

        return new CouponResponseDto(coupon.getId(), coupon.getName(), coupon.getDiscountRate());
    }

    public List<CouponResponseDto> selectListCoupon(CustomUserPrincipal customUserPrincipal) {
        User user = authRepository.findById(customUserPrincipal.getUserId())
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        List<Coupon> coupons = couponRepository.findByUserId(user.getId());

        List<CouponResponseDto> couponResponseDtos = new ArrayList<>();

        for (Coupon couponsdto : coupons) {
            couponResponseDtos.add(
                    new CouponResponseDto(couponsdto.getId(),couponsdto.getName(),couponsdto.getDiscountRate()));

        }
        return couponResponseDtos;

    }

    public CouponResponseDto updateCoupon(CouponRequestDto dto, Long couponId) {

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(()-> new BaseException(ErrorCode.COUPON_NOT_FOUND));

        coupon.update(dto.getName(), dto.getDiscountRate());

        return new CouponResponseDto(coupon.getId(), coupon.getName(), coupon.getDiscountRate());
    }

    public void deleteCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        couponRepository.delete(coupon);
    }

}
