package com.example.dream_stadium_V2.owner.match.dto;

import com.example.dream_stadium_V2.owner.team.entity.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MatchRequestDto {

    @NotNull(message = "비면 안됩니다.")
    private Long cost;

    @NotNull(message = "비면 안됩니다.")
    private LocalDateTime matchDate;

    @NotBlank(message = "비면 안됩니다.")
    private String homeTeam;

    @NotBlank(message = "비면 안됩니다.")
    private String awayTeam;

    @NotNull(message = "비면 안됩니다.")
    private Long stadiumId;

    @NotNull(message = "비면 안돕니다.")
    private Long teamId;
}
