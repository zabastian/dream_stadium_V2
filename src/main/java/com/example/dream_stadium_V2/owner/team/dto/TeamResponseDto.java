package com.example.dream_stadium_V2.owner.team.dto;

import lombok.Getter;

@Getter
public class TeamResponseDto {
    private Long teamId;
    private String name;
    private Long userId;

    public TeamResponseDto(Long teamId, String name, Long userId) {
        this.teamId = teamId;
        this.name = name;
        this.userId = userId;
    }
}
