package com.example.dream_stadium_V2.owner.match.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.match.dto.MatchRequestDto;
import com.example.dream_stadium_V2.owner.match.dto.MatchResponseDto;
import com.example.dream_stadium_V2.owner.match.service.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
