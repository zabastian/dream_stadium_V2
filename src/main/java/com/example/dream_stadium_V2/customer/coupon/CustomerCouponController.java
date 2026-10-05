package com.example.dream_stadium_V2.customer.coupon;

import com.example.dream_stadium_V2.owner.coupon.dto.CouponResponseDto;
import com.example.dream_stadium_V2.owner.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerCouponController {

    private final CouponService couponService;

    @GetMapping("/coupon/list")
    public ResponseEntity<List<CouponResponseDto>> selectedCustomerListCoupon(

    ) {
        List<CouponResponseDto> couponResponseDtos = couponService.selectCustomerListCoupon();
        return ResponseEntity.ok().body(couponResponseDtos);

    }
}
