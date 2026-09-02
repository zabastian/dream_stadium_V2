package com.example.dream_stadium_V2.owner.matchSeat.dto;

import com.example.dream_stadium_V2.owner.matchSeat.entity.SeatType;
import com.example.dream_stadium_V2.owner.seat.entity.Seat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MatchSeatResponseDto {
    private Long matchSeatId;
    private Long seatId;
    private Long matchId;
    private Long capacity;
    private boolean isReserved;
    private SeatType seatType;

    public MatchSeatResponseDto(Long matchSeatId, Long seatId, Long matchId, Long capacity, boolean isReserved, SeatType seatType) {
        this.matchSeatId = matchSeatId;
        this.seatId = seatId;
        this.matchId = matchId;
        this.capacity = capacity;
        this.isReserved = isReserved;
        this.seatType = seatType;
    }
}



