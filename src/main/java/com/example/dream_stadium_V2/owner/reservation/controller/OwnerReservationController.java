package com.example.dream_stadium_V2.owner.reservation.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.reservation.dto.OwnerReservationRequestDto;
import com.example.dream_stadium_V2.owner.reservation.dto.OwnerReservationResponseDto;
import com.example.dream_stadium_V2.owner.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerReservationController {

    private final ReservationService ownerReservationService;

    @PostMapping("/reservation/create")
    public ResponseEntity<OwnerReservationResponseDto> createdReservation(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody OwnerReservationRequestDto ownerReservationRequestDto
            ) {
        OwnerReservationResponseDto ownerReservationResponseDto = ownerReservationService.createReservation(customUserPrincipal.getUserId(), ownerReservationRequestDto);
        return ResponseEntity.ok().body(ownerReservationResponseDto);
    }
}
