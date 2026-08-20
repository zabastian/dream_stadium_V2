package com.example.dream_stadium_V2.owner.stadium.dto;


import com.example.dream_stadium_V2.common.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StadiumResponseDto {
    private Long stadiumId;
    private String name;
    private Long userId;

    public StadiumResponseDto(Long stadiumId, String name, Long userId) {
        this.stadiumId = stadiumId;
        this.name = name;
        this.userId = userId;
    }
}
