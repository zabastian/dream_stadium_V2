package com.example.dream_stadium_V2.owner.userCoupon.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.coupon.entity.Coupon;
import com.example.dream_stadium_V2.owner.coupon.repository.CouponRepository;
import com.example.dream_stadium_V2.owner.userCoupon.dto.UserCouponRequestDto;
import com.example.dream_stadium_V2.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium_V2.owner.userCoupon.entity.UserCoupon;
import com.example.dream_stadium_V2.owner.userCoupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private final UserCouponRepository userCouponRepository;
    private final AuthRepository authRepository;
    private final CouponRepository couponRepository;

    public UserCouponResponseDto createUserCoupon(CustomUserPrincipal customUserPrincipal, UserCouponRequestDto dto) {

        User user = authRepository.findById(customUserPrincipal.getUserId())
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Coupon coupon = couponRepository.findById(dto.getCouponId())
                .orElseThrow(()-> new BaseException(ErrorCode.COUPON_NOT_FOUND));

        UserCoupon userCoupon = UserCoupon.create(user, coupon, dto.getName(), dto.isUsed());

        userCouponRepository.save(userCoupon);

        return new UserCouponResponseDto(userCoupon.getId(), userCoupon.getUser().getId(), userCoupon.getCoupon().getId(), userCoupon.getName(), userCoupon.isUsed());
    }

    public void deleteUserCoupon(Long userCouponId) {
        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_COUPON_NOT_FOUND));

        userCouponRepository.delete(userCoupon);

    }

}
