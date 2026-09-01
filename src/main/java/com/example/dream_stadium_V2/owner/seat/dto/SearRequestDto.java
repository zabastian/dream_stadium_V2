package com.example.dream_stadium_V2.owner.seat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SearRequestDto {

    @NotBlank
    private String name;
}
