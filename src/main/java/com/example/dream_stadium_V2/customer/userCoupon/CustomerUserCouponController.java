package com.example.dream_stadium_V2.customer.userCoupon;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.userCoupon.dto.UserCouponRequestDto;
import com.example.dream_stadium_V2.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium_V2.owner.userCoupon.service.UserCouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerUserCouponController {

    private final UserCouponService userCouponService;

    @PostMapping("/userCoupon") // 유저쿠폰 다운로드 로직
    public ResponseEntity<UserCouponResponseDto> createdUserCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody UserCouponRequestDto userCouponRequestDto
    ) {
        UserCouponResponseDto userCouponResponseDto = userCouponService.createUserCoupon(customUserPrincipal, userCouponRequestDto);
        return ResponseEntity.ok().body(userCouponResponseDto);
    }

    @GetMapping("/userCoupon/list") // 다운로드한 유저쿠폰 가져오는 로직
    public ResponseEntity<List<UserCouponResponseDto>> selectedListCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
            ) {

        List<UserCouponResponseDto> userCouponResponseDtoList = userCouponService.selectListUserCoupon(customUserPrincipal.getUserId());

        return ResponseEntity.ok().body(userCouponResponseDtoList);

    }

    @PostMapping("/userCoupon/used/{userCouponId}") // 발급된 유저쿠폰 사용 로직
    public ResponseEntity<UserCouponResponseDto> usedUserCoupon(
            @PathVariable Long userCouponId
    ) {
        UserCouponResponseDto userCouponResponseDto = userCouponService.useUserCoupon(userCouponId);
        return ResponseEntity.ok().body(userCouponResponseDto);
    }

    @DeleteMapping("/userCoupon/delete/{userCouponId}")
    public ResponseEntity<Void> deletedUserCoupon(
            @PathVariable Long userCouponId
    ) {

        userCouponService.deleteUserCoupon(userCouponId);

        return ResponseEntity.noContent().build();
    }
}
