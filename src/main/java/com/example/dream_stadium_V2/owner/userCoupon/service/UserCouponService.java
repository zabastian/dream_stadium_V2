package com.example.dream_stadium_V2.owner.userCoupon.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


        UserCoupon userCoupon = UserCoupon.create(user, coupon, false);

        userCouponRepository.save(userCoupon);

        return new UserCouponResponseDto(userCoupon.getId(), userCoupon.getUser().getId(), userCoupon.getCoupon().getId(), userCoupon.isUsed());
    }

    public void deleteUserCoupon(Long userCouponId) {
        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_COUPON_NOT_FOUND));

        userCouponRepository.delete(userCoupon);

    }

    public List<UserCouponResponseDto> selectListUserCoupon(Long userId) {

        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        List<UserCouponResponseDto> userCouponResponseDtoList = new ArrayList<>();

        List<UserCoupon> userCouponList = userCouponRepository.findByUser_Id(user.getId());

        for (UserCoupon userCoupon : userCouponList) {
            userCouponResponseDtoList.add(
                    new UserCouponResponseDto(
                        userCoupon.getId(),
                        userCoupon.getUser().getId(),
                        userCoupon.getCoupon().getId(),
                        userCoupon.isUsed()
                    )
            );
        }

        return userCouponResponseDtoList;

    }

    @Transactional
    public UserCouponResponseDto useUserCoupon(Long userCouponId) {

        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_COUPON_NOT_FOUND));

        //set이 가능한 이유는 findById()~~가 UserCoupon userCoupon = new UserCoupon()과 같은 역할을 해주고 있기 때문;
        userCoupon.setUsed(true);

        /* userCoupon을 public void updateUserCoupon(); 으로 변경하는 방법

        boolean isUsed = userCoupon.isUsed();

        isUsed = true;

        userCoupon.updateUserCoupon(userCoupon.isUsed());
        */

        userCouponRepository.save(userCoupon);

        return new UserCouponResponseDto(userCoupon.getId(), userCoupon.getUser().getId(), userCoupon.getCoupon().getId(), userCoupon.isUsed());
    }

}
