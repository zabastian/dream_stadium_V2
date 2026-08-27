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

    @NotNull(message = "비면 안됩니다.")
    private Long homeTeamId;

    @NotNull(message = "비면 안됩니다.")
    private Long awayTeamId;

    @NotNull(message = "비면 안됩니다.")
    private Long stadiumId;

}
