package com.example.dream_stadium_V2.owner.seat.dto;

import lombok.Getter;

@Getter
public class SeatResponseDto {
    private Long seatId;
    private String name;
    private Long userId;

    public SeatResponseDto(Long seatId, String name, Long userId) {
        this.seatId = seatId;
        this.name = name;
        this.userId = userId;
    }

}
