package com.example.dream_stadium_V2.owner.match.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.match.dto.MatchRequestDto;
import com.example.dream_stadium_V2.owner.match.dto.MatchResponseDto;
import com.example.dream_stadium_V2.owner.match.service.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/match")
    public ResponseEntity<MatchResponseDto> createdMatch(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody MatchRequestDto matchRequestDto
    ) {

        MatchResponseDto matchResponseDto = matchService.createMatch(customUserPrincipal.getUserId(), matchRequestDto);

        return ResponseEntity.ok().body(matchResponseDto);
    }

    @PostMapping("/match/update/{matchId}") //생성 userId는 변경될 일 이 없을 예정 즉, 기존 update에 내용 추가 x
    public ResponseEntity<MatchResponseDto> updatedMatch(
            @Valid @RequestBody MatchRequestDto matchRequestDto,
            @PathVariable Long matchId
    ) {
        MatchResponseDto matchResponseDto = matchService.updateMatch(matchRequestDto, matchId);
        return ResponseEntity.ok().body(matchResponseDto);
    }

    @GetMapping("/match/list")
    public ResponseEntity<List<MatchResponseDto>> selectedListMatch(
    ) {
        List<MatchResponseDto> matchResponseDtos = matchService.selectListMatch();
        return ResponseEntity.ok().body(matchResponseDtos);
    }

    @DeleteMapping("/match/delete/{matchId}")
    public ResponseEntity<Void> deletedMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok().build();
    }

    //selectlistmatch
}
