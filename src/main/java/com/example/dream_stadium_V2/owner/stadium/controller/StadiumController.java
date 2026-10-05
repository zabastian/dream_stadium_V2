package com.example.dream_stadium_V2.owner.stadium.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.stadium.dto.StadiumResponseDto;
import com.example.dream_stadium_V2.owner.stadium.dto.StadiumRequestDto;
import com.example.dream_stadium_V2.owner.stadium.entity.Stadium;
import com.example.dream_stadium_V2.owner.stadium.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping("/stadium")
    public ResponseEntity<StadiumResponseDto> createdStadium(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody StadiumRequestDto stadiumRequestDto
    ) {
        StadiumResponseDto stadiumResponseDto = stadiumService.createStadium(customUserPrincipal.getUserId(), stadiumRequestDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(stadiumResponseDto);
    }

    @PostMapping("/stadium/update/{stadiumId}")
    public ResponseEntity<StadiumResponseDto> updatedStadium(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody StadiumRequestDto stadiumRequestDto,
            @PathVariable Long stadiumId
    ) {
        StadiumResponseDto stadiumResponseDto = stadiumService.updateStadium(customUserPrincipal.getUserId(), stadiumRequestDto.getName(), stadiumId);

        return ResponseEntity.ok(stadiumResponseDto);
    }

    @DeleteMapping("/stadium/delete/{stadiumId}")
    public ResponseEntity<Void> deletedStadium(
            @PathVariable Long stadiumId
    ) {
        stadiumService.deleteStadium(stadiumId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("stadium/list") // 해당 owner가 만든 경기장들 불러오는 api
    public ResponseEntity<List<StadiumResponseDto>> selectedListStadium(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
    ) {
        List<StadiumResponseDto> stadiumResponseDto = stadiumService.selectListStadium(customUserPrincipal.getUserId());
        return ResponseEntity.ok(stadiumResponseDto);
    }
}
