package com.example.dream_stadium_V2.owner.matchSeat.entity;

import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.example.dream_stadium_V2.owner.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CollectionId;

@Entity
@Getter
public class MatchSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchSeat_id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seats_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "capacity") // 경기별 마다 좌석 수 다르게 설정할 것 이기 때문에 capacitu는 중간 엔티티
    private Long capacity;

    @Enumerated(EnumType.STRING) // 기본 b 클래스 a 클래스일시 비용 10% 추가
    private SeatType seatType;

    private boolean isReserved = false;

    public static MatchSeat createMatchSeat(Seat seat, Match match, Long capacity, SeatType seatType, boolean isReserved) {
        MatchSeat matchSeat = new MatchSeat();
        matchSeat.seat = seat;
        matchSeat.match = match;
        matchSeat.capacity = capacity;
        matchSeat.seatType = seatType;
        matchSeat.isReserved = isReserved;
        return matchSeat;
    }

    public void updateMatchSeat(Long capacity, SeatType seatType, boolean isReserved) {
        this.capacity = capacity;
        this.seatType = seatType;
        this.isReserved = isReserved;
    }
}
