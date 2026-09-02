package com.example.dream_stadium_V2.owner.matchSeat.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchSeatRepository extends JpaRepository<MatchSeat, Long> {
    List<MatchSeat> findByMatchUser(User user);

    Long match(Match match);
}
