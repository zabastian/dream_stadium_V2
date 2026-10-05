package com.example.dream_stadium_V2.owner.match.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.owner.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser(User user);

}
