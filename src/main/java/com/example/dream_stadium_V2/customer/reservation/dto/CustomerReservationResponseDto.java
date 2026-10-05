package com.example.dream_stadium_V2.customer.reservation.dto;

import lombok.Getter;

@Getter
public class CustomerReservationResponseDto {
    private Long id;
    private String name;
    private Long userId;
    private Long matchSeatId;
    private Long cost;
    private boolean isReserved;

    public CustomerReservationResponseDto(Long id, String name ,Long userId, Long matchSeatId, Long cost, boolean isReserved) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.matchSeatId = matchSeatId;
        this.cost = cost;
        this.isReserved = isReserved;
    }
}
