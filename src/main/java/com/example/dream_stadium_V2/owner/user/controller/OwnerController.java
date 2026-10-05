package com.example.dream_stadium_V2.owner.user.controller;

import com.example.dream_stadium_V2.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium_V2.owner.user.dto.OwnerResponseDto;
import com.example.dream_stadium_V2.owner.user.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/individual")
    public ResponseEntity<OwnerResponseDto> selectedOwner(@AuthenticationPrincipal CustomUserPrincipal customUserPrincipal) {
        Long userId = customUserPrincipal.getUserId();
        OwnerResponseDto ownerResponseDto = ownerService.selectOwner(userId);
        return ResponseEntity.ok(ownerResponseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OwnerResponseDto>> selectedListOwner() {
        List<OwnerResponseDto> ownerResponseDto = ownerService.selectListOwner();
        return ResponseEntity.ok(ownerResponseDto);
    }
}
