package com.example.dream_stadium_V2.owner.matchSeat.dto;

import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.example.dream_stadium_V2.owner.matchSeat.entity.SeatType;
import com.example.dream_stadium_V2.owner.seat.entity.Seat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MatchSeatRequestDto {

    private Long seatId;

    private Long matchId;

    @NotNull(message = "빈값이 존재합니다. ")
    private Long capacity;

    private boolean isReserved; // boolean은 빈 값 null이기 떄문에 소용 x

    @NotNull(message = "빈값이 존재합니다. ")
    private SeatType seatType;


}
