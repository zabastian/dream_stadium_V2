package com.example.dream_stadium_V2.owner.matchSeat.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchSeatRepository extends JpaRepository<MatchSeat, Long> {
    List<MatchSeat> findByMatchUser(User user);

    Long match(Match match);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM MatchSeat m WHERE m.id = :matchSeatIds" )
    Optional<MatchSeat> findByIdForUpdate(@Param("matchSeatIds") Long matchSeatId);
}
