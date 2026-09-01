package com.example.dream_stadium_V2.owner.seat.repository;

import com.example.dream_stadium_V2.owner.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatById(Long seatId);
}
