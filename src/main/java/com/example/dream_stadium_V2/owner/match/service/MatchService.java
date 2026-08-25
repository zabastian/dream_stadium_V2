package com.example.dream_stadium_V2.owner.match.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.match.dto.MatchRequestDto;
import com.example.dream_stadium_V2.owner.match.dto.MatchResponseDto;
import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.example.dream_stadium_V2.owner.match.repository.MatchRepository;
import com.example.dream_stadium_V2.owner.stadium.entity.Stadium;
import com.example.dream_stadium_V2.owner.stadium.repository.StadiumRepository;
import com.example.dream_stadium_V2.owner.team.entity.Team;
import com.example.dream_stadium_V2.owner.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final AuthRepository authRepository;
    private final TeamRepository teamRepository;
    private final StadiumRepository stadiumRepository;

    public MatchResponseDto createMatch(Long userId, MatchRequestDto dto) {

        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(()-> new BaseException(ErrorCode.STADIUM_NOT_FOUND));

        Match match = Match.create(dto.getCost(), dto.getMatchDate(), dto.getHomeTeam(), dto.getAwayTeam(), user, stadium, team);

        Match savedMatch = matchRepository.save(match);

        return new MatchResponseDto(savedMatch);
    }

}
