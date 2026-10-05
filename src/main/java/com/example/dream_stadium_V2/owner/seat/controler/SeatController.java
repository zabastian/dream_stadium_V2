package com.example.dream_stadium_V2.owner.seat.controler;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.seat.dto.SearRequestDto;
import com.example.dream_stadium_V2.owner.seat.dto.SeatResponseDto;
import com.example.dream_stadium_V2.owner.seat.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<Void> createdSeat( //해당 owner의 userId 예외처리 사용
            @Valid @RequestBody SearRequestDto seatRequestDto,
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
            ) {
        seatService.createSeat(seatRequestDto, customUserPrincipal.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/seat/list")
    public ResponseEntity<List<SeatResponseDto>> selectedListSeat(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
    ) {
        List<SeatResponseDto> seatResponseDtoList = seatService.selectListSeat(customUserPrincipal.getUserId());
        return ResponseEntity.ok().body(seatResponseDtoList);
    }

    @DeleteMapping("/seat/delete/{seatId}")
    public ResponseEntity<Void> deletedSeat(
            @PathVariable Long seatId
    ) {
        seatService.deleteSeat(seatId);
        return ResponseEntity.noContent().build();
    }
}
