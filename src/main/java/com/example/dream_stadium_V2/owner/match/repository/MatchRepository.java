package com.example.dream_stadium_V2.owner.match.repository;

import com.example.dream_stadium_V2.owner.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
