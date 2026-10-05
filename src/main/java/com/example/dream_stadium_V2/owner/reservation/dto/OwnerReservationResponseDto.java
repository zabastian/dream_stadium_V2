package com.example.dream_stadium_V2.owner.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OwnerReservationResponseDto {

    private Long id;
    private String name;
    private Long userId;
    private Long matchSeatId;
    private Long cost;


    public OwnerReservationResponseDto(Long id, String name, Long userId, Long matchSeatId, Long cost) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.matchSeatId = matchSeatId;
        this.cost = cost;

    }
}
