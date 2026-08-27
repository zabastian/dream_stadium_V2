package com.example.dream_stadium_V2.owner.match.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        Team homeTeam = teamRepository.findById(dto.getHomeTeamId())
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        Team awayTeam = teamRepository.findById(dto.getAwayTeamId())
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));


        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(()-> new BaseException(ErrorCode.STADIUM_NOT_FOUND));

        Match match = Match.create(dto.getCost(), dto.getMatchDate(), homeTeam, awayTeam, user, stadium);

        Match savedMatch = matchRepository.save(match);

        return new MatchResponseDto(savedMatch);
    }

    @Transactional
    public MatchResponseDto updateMatch(MatchRequestDto dto, Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new BaseException(ErrorCode.MATCH_NOT_FOUNT));

        Team homeTeam = teamRepository.findById(dto.getHomeTeamId())
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        Team awayTeam = teamRepository.findById(dto.getAwayTeamId())
                .orElseThrow(() -> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(() -> new BaseException(ErrorCode.STADIUM_NOT_FOUND));

        match.update(match.getCost(), match.getMatchDate(), homeTeam, awayTeam, stadium);

        return new MatchResponseDto(match);
    }

    @Transactional
    public List<MatchResponseDto> selectListMatch() {

        List<User> user = authRepository.findByUserRole(UserRole.OWNER);

        if(user.isEmpty()) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        List<Match> matches = new ArrayList<>();

        for (User users : user) {
            List<Match> userMatches = matchRepository.findByUser(users);
            matches.addAll(userMatches);
        }

        List<MatchResponseDto> response = new ArrayList<>();

        for(Match match : matches) {
            response.add(new MatchResponseDto(match)); // 팩토리 메소드 사용안함, 응답 여러개 만들 생각 없기에, 그냥 바로 생성자 만듬
        }

        return response;
    }

    public void deleteMatch(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(()->new BaseException(ErrorCode.MATCH_NOT_FOUNT));

        matchRepository.delete(match);
    }

}
