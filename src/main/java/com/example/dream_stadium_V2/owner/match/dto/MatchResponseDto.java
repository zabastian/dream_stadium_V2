package com.example.dream_stadium_V2.owner.match.dto;

import com.example.dream_stadium_V2.owner.match.entity.Match;

import java.time.LocalDateTime;

public class MatchResponseDto {

    private Long matchId;
    private Long cost;
    private LocalDateTime matchDate;
    private String homeTeam;
    private String awayTeam;
    private Long userId;
    private Long stadiumId;
    private Long teamId;

    public MatchResponseDto(Match match) {
        this.matchId = match.getId();
        this.cost = match.getCost();
        this.matchDate = match.getMatchDate();
        this.homeTeam = match.getHomeTeam();
        this.awayTeam = match.getAwayTeam();
        this.userId = match.getUser().getId();
        this.stadiumId = match.getStadium().getId();
        this.teamId = match.getTeam().getId();

    }

}
