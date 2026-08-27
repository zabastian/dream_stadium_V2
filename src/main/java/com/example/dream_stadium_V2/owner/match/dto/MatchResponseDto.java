package com.example.dream_stadium_V2.owner.match.dto;

import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({
        "matchId",
        "cost",
        "matchDate",
        "homeTeamId",
        "awayTeamId",
        "userId",
        "stadiumId"
})
public class MatchResponseDto {

    private Long matchId;
    private Long cost;
    private LocalDateTime matchDate;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long userId;
    private Long stadiumId;

    public MatchResponseDto(Match match) {
        this.matchId = match.getId();
        this.cost = match.getCost();
        this.matchDate = match.getMatchDate();
        this.homeTeamId = match.getHomeTeam().getId();
        this.awayTeamId = match.getAwayTeam().getId();
        this.userId = match.getUser().getId();
        this.stadiumId = match.getStadium().getId();

    }
}
