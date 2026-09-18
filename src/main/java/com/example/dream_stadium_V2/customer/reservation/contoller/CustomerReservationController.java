package com.example.dream_stadium_V2.customer.reservation.contoller;

import com.example.dream_stadium_V2.common.auth.oauth.CustomOAuth2UserService;
import com.example.dream_stadium_V2.customer.reservation.dto.CustomerReservationRequestDto;
import com.example.dream_stadium_V2.customer.reservation.dto.CustomerReservationResponseDto;
import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/list")     //owner의 reservation 조회하기
    public ResponseEntity<List<CustomerReservationResponseDto>> selectedListReservation() {
        List<CustomerReservationResponseDto> customerReservationResponseDto = reservationService.selectListReservation();
        return ResponseEntity.ok().body(customerReservationResponseDto);
    }

    // .유저쿠폰 사용해서 할인하기
    // .예약 된것은 true로 반환하기
    @PostMapping("/reservation/{reservationId}") //예약하기(ownwer의 예약 중 해당하느것을 선택)
    public ResponseEntity<CustomerReservationResponseDto> customerReservationResponseDto(
            @PathVariable Long reservationId,
            @Valid @RequestBody CustomerReservationRequestDto customerReservationRequestDto,
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
            ) {

        CustomerReservationResponseDto customerReservationResponseDto = reservationService.createCustomerReservation(reservationId, customerReservationRequestDto.getUserCouponId(), customUserPrincipal.getUserId());
        return ResponseEntity.ok().body(customerReservationResponseDto);

    }

    @GetMapping("/reservation/customerList")
    public ResponseEntity<List<CustomerReservationResponseDto>> selectedListCustomerReservation(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
    ) {
        List<CustomerReservationResponseDto> customerReservationResponseDtos = reservationService.selectListCustomerReservation(customUserPrincipal.getUserId());
        return ResponseEntity.ok().body(customerReservationResponseDtos);
    }


    // 예약된 쿠폰 리스트 개인 볼 수 있는 api (selectedListReservation는 예약 안된것도 보여지니까 필요하다.)

    // 예약된 쿠폰 취소 deleteMapping 사용 안하고 내역 볼 수있게끔 isReserved로 보여줄 예정
}
