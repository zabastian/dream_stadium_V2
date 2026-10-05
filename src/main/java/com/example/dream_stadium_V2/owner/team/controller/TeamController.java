package com.example.dream_stadium_V2.owner.team.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.team.dto.TeamRequestDto;
import com.example.dream_stadium_V2.owner.team.dto.TeamResponseDto;
import com.example.dream_stadium_V2.owner.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequiredArgsConstructor
    @RequestMapping("/owner")
    public class TeamController {

        private final TeamService teamService;

        @PostMapping("/team")
        public ResponseEntity<Void> createdTeam(
                @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
                @RequestBody TeamRequestDto teamRequestDto
        ) {
            teamService.createTeam(customUserPrincipal.getUserId(), teamRequestDto.getName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @PostMapping("/team/update/{teamId}")
        public ResponseEntity<TeamResponseDto> updatedTeam(
                @RequestBody TeamRequestDto teamRequestDto,
                @PathVariable Long teamId
        ) {
            TeamResponseDto teamResponseDto = teamService.updateTeam(teamRequestDto.getName(), teamId);
            return ResponseEntity.ok().body(teamResponseDto);
        }

        @GetMapping("/team/list") // // 해당 owner가 만든 팀을 불러오는 api
        public ResponseEntity<List<TeamResponseDto>> selectedListTeam(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
        ) {

            List<TeamResponseDto> teamResponseDto = teamService.selectListTeam(customUserPrincipal.getUserId());
            return ResponseEntity.ok().body(teamResponseDto);

        }

        @DeleteMapping("/team/delete/{teamId}")
    public ResponseEntity<Void> deletedTeam(
            @PathVariable Long teamId
        ) {
            teamService.deleteTeam(teamId);
            return ResponseEntity.noContent().build();
        }


    }
