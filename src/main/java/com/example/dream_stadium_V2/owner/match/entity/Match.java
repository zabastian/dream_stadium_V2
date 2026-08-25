package com.example.dream_stadium_V2.owner.match.entity;

import com.example.dream_stadium_V2.common.user.baseentity.BaseEntity;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.owner.stadium.entity.Stadium;
import com.example.dream_stadium_V2.owner.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Match")
@NoArgsConstructor
@Getter
public class Match extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "cost", nullable = false)
    private Long cost;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Column(name = "home_team", nullable = false)
    private String homeTeam;

    @Column(name = "away_team", nullable = false)
    private String awayTeam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public static Match create(Long cost, LocalDateTime matchDate, String homeTeam, String awayTeam, User user, Stadium stadium, Team team) {
        Match match = new Match();
        match.cost = cost;
        match.matchDate = matchDate;
        match.homeTeam = homeTeam;
        match.awayTeam = awayTeam;
        match.user = user;
        match.stadium = stadium;
        match.team = team;
        return match;
    }

}
