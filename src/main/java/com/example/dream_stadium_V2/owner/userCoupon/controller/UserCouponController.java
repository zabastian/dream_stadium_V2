package com.example.dream_stadium_V2.owner.userCoupon.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.userCoupon.dto.UserCouponRequestDto;
import com.example.dream_stadium_V2.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium_V2.owner.userCoupon.service.UserCouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class UserCouponController {

    private final UserCouponService userCouponService;

    @PostMapping("/userCoupon")
    public ResponseEntity<UserCouponResponseDto> createdUserCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody UserCouponRequestDto userCouponRequestDto
    ) {
        UserCouponResponseDto userCouponResponseDto = userCouponService.createUserCoupon(customUserPrincipal, userCouponRequestDto);
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
