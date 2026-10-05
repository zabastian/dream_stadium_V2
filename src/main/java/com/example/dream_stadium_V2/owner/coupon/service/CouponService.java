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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final AuthRepository authRepository;

    public CouponResponseDto createCoupon(CouponRequestDto dto, CustomUserPrincipal customUserPrincipal) {

        User user = authRepository.findById(customUserPrincipal.getUserId())
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Coupon coupon  = Coupon.create(dto.getName(), dto.getDiscountRate(), user);

        couponRepository.save(coupon);

        return new CouponResponseDto(coupon.getId(), coupon.getName(), coupon.getDiscountRate());
    }

    public List<CouponResponseDto> selectListCoupon(CustomUserPrincipal customUserPrincipal) {

        System.out.println("principal userId = " + customUserPrincipal.getUserId());

        User user = authRepository.findById(customUserPrincipal.getUserId())
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));


        System.out.println("찾은 user id = " + user.getId());

        List<Coupon> coupons = couponRepository.findByUser_Id(user.getId());

        System.out.println("쿠폰 개수 = " + coupons.size());

        List<CouponResponseDto> couponResponseDtos = new ArrayList<>();

        for (Coupon couponsdto : coupons) {
            couponResponseDtos.add(
                    new CouponResponseDto(couponsdto.getId(),couponsdto.getName(),couponsdto.getDiscountRate()));

        }
        return couponResponseDtos;

    }

    @Transactional
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

    public List<CouponResponseDto> selectCustomerListCoupon() {

        List<Coupon> coupon = couponRepository.findAll();

        List<CouponResponseDto> couponList = new ArrayList<>();

        for (Coupon coupons : coupon) {
            couponList.add(
                    new CouponResponseDto(
                            coupons.getId(),
                            coupons.getName(),
                            coupons.getDiscountRate()
                    )
            );

        }

        return couponList;
    }

}
