package com.example.dream_stadium_V2.owner.stadium.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.stadium.dto.StadiumResponseDto;
import com.example.dream_stadium_V2.owner.stadium.entity.Stadium;
import com.example.dream_stadium_V2.owner.stadium.repository.StadiumRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final AuthRepository authRepository;

    public StadiumService(StadiumRepository stadiumRepository, AuthRepository authRepository) {
        this.stadiumRepository = stadiumRepository;
        this.authRepository = authRepository;

    }

    public StadiumResponseDto createStadium(Long userId, String name) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Stadium stadium = Stadium.create(name, user);

        stadiumRepository.save(stadium);

        return new StadiumResponseDto(stadium.getId(), stadium.getName(), stadium.getUser().getId());
    }

    // -------- 20260820_생성자로 만들던 로직 => .create 팩토리 메소드 변경  --------
    /*

    public StadiumResponseDto create(Long userId, String name) {

        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Stadium stadium = new Stadium();

        stadium.name = name;
        stadium.user = user;

        stadiumRepository.save(stadium);

        return new StadiumResponseDto(
                stadium.id,
                stadium.name,
                stadium.user.getId()
        );
    }*/

    public StadiumResponseDto updateStadium(Long userId, String name, Long stadiumId) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new BaseException(ErrorCode.STADIUM_NOT_FOUND));

        stadium.updateName(name);

        stadiumRepository.save(stadium);

        return new StadiumResponseDto(stadium.getId(), stadium.getName(), stadium.getUser().getId());

    }

    public void deleteStadium(Long stadiumId) {

        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(()-> new BaseException(ErrorCode.STADIUM_NOT_FOUND));

        stadiumRepository.delete(stadium);
    }

    public List<StadiumResponseDto> selectListStadium(Long userId) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        List<Stadium> stadiumList = stadiumRepository.findAllByUser(user);

        List<StadiumResponseDto> stadiumListEmpty = new ArrayList<>();

        for (Stadium stadium : stadiumList) {
            stadiumListEmpty.add(
                    new StadiumResponseDto(
                            stadium.getId(),
                            stadium.getName(),
                            stadium.getUser().getId()
                    )
            );
        }

        return stadiumListEmpty;
    }
}
