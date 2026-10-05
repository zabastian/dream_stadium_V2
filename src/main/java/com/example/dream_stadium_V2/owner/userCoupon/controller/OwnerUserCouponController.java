/*
package com.example.dream_stadium_V2.owner.userCoupon.controller;

import com.example.dream_stadium_V2.owner.userCoupon.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class OwnerUserCouponController {

    private final UserCouponService userCouponService;

    @DeleteMapping("/userCoupon/delete/{userCouponId}")
    public ResponseEntity<Void> deletedUserCoupon(
            @PathVariable Long userCouponId
    ) {
        userCouponService.deleteUserCoupon(userCouponId);
        return ResponseEntity.noContent().build();
    }
}
*/
