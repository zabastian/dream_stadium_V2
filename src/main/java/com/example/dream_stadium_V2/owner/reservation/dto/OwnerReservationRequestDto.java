package com.example.dream_stadium_V2.owner.reservation.dto;

import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OwnerReservationRequestDto {

    @NotNull
    private Long matchSeatId;

    @NotBlank
    private String name;

    @NotNull
    private Long cost;

    private boolean isReserved;

}
