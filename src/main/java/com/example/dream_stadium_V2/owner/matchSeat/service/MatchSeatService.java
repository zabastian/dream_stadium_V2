package com.example.dream_stadium_V2.owner.matchSeat.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.match.entity.Match;
import com.example.dream_stadium_V2.owner.match.repository.MatchRepository;
import com.example.dream_stadium_V2.owner.matchSeat.dto.MatchSeatRequestDto;
import com.example.dream_stadium_V2.owner.matchSeat.dto.MatchSeatResponseDto;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import com.example.dream_stadium_V2.owner.matchSeat.repository.MatchSeatRepository;
import com.example.dream_stadium_V2.owner.seat.entity.Seat;
import com.example.dream_stadium_V2.owner.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchSeatService {

    private final AuthRepository authRepository;
    private final MatchRepository matchRepository;
    private final SeatRepository seatRepository;
    private final MatchSeatRepository matchSeatRepository;

    public MatchSeatResponseDto createMatchSeat(Long id, MatchSeatRequestDto dto) {

        User user = authRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(()-> new BaseException(ErrorCode.MATCH_NOT_FOUND));

        Seat seat = seatRepository.findById(dto.getSeatId())
                .orElseThrow(()-> new BaseException(ErrorCode.SEAT_NOT_FOUND));

        MatchSeat matchSeat = MatchSeat.createMatchSeat(seat, match, dto.getCapacity(), dto.getSeatType(), dto.isReserved());

        matchSeatRepository.save(matchSeat);

        return new MatchSeatResponseDto(
                matchSeat.getId(),
                matchSeat.getSeat().getId(),
                matchSeat.getMatch().getId(),
                matchSeat.getCapacity(),
                matchSeat.isReserved(),
                matchSeat.getSeatType());
    }

    public MatchSeatResponseDto updateMatchSeat(MatchSeatRequestDto matchSeatRequestDto, Long matchSeatId) {
        MatchSeat matchSeat = matchSeatRepository.findById(matchSeatId)
                .orElseThrow(()-> new BaseException(ErrorCode.MATCH_SEAT_NOT_FOUND));

        matchSeat.updateMatchSeat(matchSeatRequestDto.getCapacity(), matchSeatRequestDto.getSeatType(), matchSeatRequestDto.isReserved());

        matchSeatRepository.save(matchSeat);

        return new MatchSeatResponseDto(
                matchSeat.getId(),
                matchSeat.getSeat().getId(),
                matchSeat.getMatch().getId(),
                matchSeat.getCapacity(),
                matchSeat.isReserved(),
                matchSeat.getSeatType());
    }

    public List<MatchSeatResponseDto> selectListMatchSeat() {
        List<User> user = authRepository.findByUserRole(UserRole.OWNER);

        if (user.isEmpty()) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        List<MatchSeat> matchSeat = new ArrayList<>();

        for (User users : user) {
            matchSeat.addAll(matchSeatRepository.findByMatchUser(users));
        }

        List<MatchSeatResponseDto> matchSeatResponseDtoList = new ArrayList<>();

        for (MatchSeat matchSeatOwner : matchSeat) {
            matchSeatResponseDtoList.add(
                    new MatchSeatResponseDto(
                            matchSeatOwner.getId(),
                            matchSeatOwner.getSeat().getId(),
                            matchSeatOwner.getMatch().getId(),
                            matchSeatOwner.getCapacity(),
                            matchSeatOwner.isReserved(),
                            matchSeatOwner.getSeatType()

                    )
            );
        }

        return matchSeatResponseDtoList;
    }

    public MatchSeatResponseDto selectMatchSeat(Long matchSeatId) {
        MatchSeat matchSeat = matchSeatRepository.findById(matchSeatId)
                .orElseThrow(()-> new BaseException(ErrorCode.MATCH_SEAT_NOT_FOUND));


        return new MatchSeatResponseDto(
                matchSeat.getId(),
                matchSeat.getSeat().getId(),
                matchSeat.getMatch().getId(),
                matchSeat.getCapacity(),
                matchSeat.isReserved(),
                matchSeat.getSeatType());
    }

    public void deleteMatchSeat(Long matchSeatId) {
        MatchSeat matchSeat = matchSeatRepository.findById(matchSeatId)
                .orElseThrow(()-> new BaseException(ErrorCode.MATCH_SEAT_NOT_FOUND));

        matchSeatRepository.delete(matchSeat);

    }
}
