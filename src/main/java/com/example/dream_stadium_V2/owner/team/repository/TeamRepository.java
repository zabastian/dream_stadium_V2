package com.example.dream_stadium_V2.owner.team.repository;

import com.example.dream_stadium_V2.owner.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByUserId(Long id);
}
