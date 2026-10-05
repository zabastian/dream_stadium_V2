package com.example.dream_stadium_V2.owner.user.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.user.dto.OwnerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final AuthRepository authRepository;

    public OwnerResponseDto selectOwner(Long userId) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return new OwnerResponseDto(user);
    }

    public List<OwnerResponseDto> selectListOwner() {
        List<User> user = authRepository.findByUserRole(UserRole.OWNER);

        List<OwnerResponseDto> emptyUser = new ArrayList<>();

        for (User userList : user) {
            emptyUser.add(new OwnerResponseDto(userList));
        }

        return emptyUser;
    }
}
