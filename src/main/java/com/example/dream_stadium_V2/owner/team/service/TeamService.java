package com.example.dream_stadium_V2.owner.team.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.team.dto.TeamResponseDto;
import com.example.dream_stadium_V2.owner.team.entity.Team;
import com.example.dream_stadium_V2.owner.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final AuthRepository authRepository;

    public void createTeam(Long userId, String name) {

        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Team team = Team.create(user, name);

        teamRepository.save(team);
    }

    public List<TeamResponseDto> selectListTeam(Long userId) {

        List<Team> team = teamRepository.findByUserId(userId);

        List<TeamResponseDto> teamResponseDtoList = new ArrayList<>();

        for (Team team1 : team) {
            teamResponseDtoList.add(
                new TeamResponseDto(
                    team1.getId(),
                    team1.getName(),
                    team1.getUser().getId()
                )
            );

        }
        return teamResponseDtoList;
    }

    public TeamResponseDto updateTeam(String name, Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        team.setName(name);

        teamRepository.save(team);

        return new TeamResponseDto(team.getId(), team.getName(), team.getUser().getId());
    }

    public void deleteTeam(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new BaseException(ErrorCode.TEAM_NOT_FOUND));

        teamRepository.delete(team);
    }
}
