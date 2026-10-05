package com.example.dream_stadium_V2.owner.reservation.service;

import com.example.dream_stadium_V2.customer.reservation.dto.CustomerReservationResponseDto;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.global.lock.DistributedLockService;
import com.example.dream_stadium_V2.owner.reservation.entity.Reservation;
import com.example.dream_stadium_V2.owner.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationLockService {

    private final DistributedLockService distributedLockService;
    private final ReservationService reservationService;

    public CustomerReservationResponseDto createCustomerReservation(
            Long reservationId,
            Long userCouponId,
            Long userId
    ) {


        String lockKey = "lock:matchSeat:" + reservationId;

        return distributedLockService.executeWithLock(
                lockKey,
                () -> reservationService.createCustomerReservation(
                        reservationId,
                        userCouponId,
                        userId
                )
        );
    }
}
