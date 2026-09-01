package com.example.dream_stadium_V2.owner.seat.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.seat.dto.SearRequestDto;
import com.example.dream_stadium_V2.owner.seat.dto.SeatResponseDto;
import com.example.dream_stadium_V2.owner.seat.entity.Seat;
import com.example.dream_stadium_V2.owner.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final AuthRepository authRepository;

    public void createSeat(SearRequestDto dto, Long userId) {
        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Seat seat = Seat.create(dto.getName());

        seatRepository.save(seat);
    }

    public List<SeatResponseDto> selectListSeat(Long userId) {

        List<User> users = authRepository.findByUserRole(UserRole.OWNER);

        if(users.isEmpty()) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        List<Seat> seats = seatRepository.findAll();

        List<SeatResponseDto> seatList = new ArrayList<>();

        for (Seat seat : seats) {
            seatList.add(new SeatResponseDto(seat.getId(),seat.getName(), userId));
        }

        return seatList;
    }

    public void deleteSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new BaseException(ErrorCode.SEAT_NOT_FOUND));

        seatRepository.delete(seat);

    }
}
