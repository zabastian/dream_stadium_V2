package com.example.dream_stadium_V2.owner.matchSeat.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.match.dto.MatchResponseDto;
import com.example.dream_stadium_V2.owner.matchSeat.dto.MatchSeatRequestDto;
import com.example.dream_stadium_V2.owner.matchSeat.dto.MatchSeatResponseDto;
import com.example.dream_stadium_V2.owner.matchSeat.service.MatchSeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class MatchSeatController {

    private final MatchSeatService matchSeatService;

    @PostMapping("/matchSeat")
    public ResponseEntity<MatchSeatResponseDto> createdMatchSeat(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody MatchSeatRequestDto matchSeatRequestDto
            ) {
        MatchSeatResponseDto matchSeatResponseDto = matchSeatService.createMatchSeat(customUserPrincipal.getUserId(), matchSeatRequestDto
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(matchSeatResponseDto);
    }

    @PostMapping("/matchSeat/update/{matchSeatId}")
    public ResponseEntity<MatchSeatResponseDto> updatedMatchSeat(
            @Valid @RequestBody MatchSeatRequestDto matchSeatRequestDto,
            @PathVariable Long matchSeatId
    ) {
        MatchSeatResponseDto matchSeatResponseDto = matchSeatService.updateMatchSeat(matchSeatRequestDto, matchSeatId);
        return ResponseEntity.ok().body(matchSeatResponseDto);
    }

    @GetMapping("/matchSeat/list") // owner가 만든 것에대한 list전체 출력 ()
    public ResponseEntity<List<MatchSeatResponseDto>> selectedListMatchSeat(
    ) {
        List<MatchSeatResponseDto> matchSeatResponseDtoList = matchSeatService.selectListMatchSeat();

        return ResponseEntity.ok().body(matchSeatResponseDtoList);
    }

    @GetMapping("/matchSeat")
    public ResponseEntity<MatchSeatResponseDto> selectedMatchSeat(
            @RequestParam Long matchSeatId
    ) {
        MatchSeatResponseDto matchSeatResponseDto = matchSeatService.selectMatchSeat(matchSeatId);
        return ResponseEntity.ok().body(matchSeatResponseDto);
    }

    @DeleteMapping("/matchSeat/delete/{matchSeatId}")
    public ResponseEntity<Void> deletedMatchSeat(
            @PathVariable Long matchSeatId
    ) {
        matchSeatService.deleteMatchSeat(matchSeatId);

        return ResponseEntity.noContent().build();
    }

}
