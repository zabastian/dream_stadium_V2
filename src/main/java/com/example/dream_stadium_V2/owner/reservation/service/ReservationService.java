package com.example.dream_stadium_V2.owner.reservation.service;

import com.example.dream_stadium_V2.common.auth.repository.AuthRepository;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.example.dream_stadium_V2.customer.reservation.dto.CustomerReservationRequestDto;
import com.example.dream_stadium_V2.customer.reservation.dto.CustomerReservationResponseDto;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.global.exception.ErrorCode;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import com.example.dream_stadium_V2.owner.matchSeat.entity.SeatType;
import com.example.dream_stadium_V2.owner.matchSeat.repository.MatchSeatRepository;
import com.example.dream_stadium_V2.owner.reservation.dto.OwnerReservationRequestDto;
import com.example.dream_stadium_V2.owner.reservation.dto.OwnerReservationResponseDto;
import com.example.dream_stadium_V2.owner.reservation.entity.Reservation;
import com.example.dream_stadium_V2.owner.reservation.repository.ReservationRepository;
import com.example.dream_stadium_V2.owner.userCoupon.entity.UserCoupon;
import com.example.dream_stadium_V2.owner.userCoupon.repository.UserCouponRepository;
import com.example.dream_stadium_V2.owner.userCoupon.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository ownerReservationRepository;
    private final AuthRepository authRepository;
    private final MatchSeatRepository matchSeatRepository;
    private final ReservationRepository reservationRepository;
    private final UserCouponService userCouponService;
    private final UserCouponRepository userCouponRepository;

    public OwnerReservationResponseDto createReservation(Long userId, OwnerReservationRequestDto dto) {

        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        MatchSeat matchSeat = matchSeatRepository.findById(dto.getMatchSeatId())
                .orElseThrow(()-> new BaseException(ErrorCode.MATCH_SEAT_NOT_FOUND));

        Reservation reservation = Reservation.create(dto.getName(), user, matchSeat, dto.getCost(), dto.isReserved());

        if (reservation.getMatchSeat().getSeatType() == SeatType.A_CLASS) {
            reservation.setCost((long) (reservation.getCost() * 0.9));
        } else {
            reservation.setCost((long) (reservation.getCost() * 0.8));
        }

        ownerReservationRepository.save(reservation);

        return new OwnerReservationResponseDto(
                reservation.getId(),
                reservation.getName(),
                reservation.getUser().getId(),
                reservation.getMatchSeat().getId(),
                reservation.getCost()
        );
    }

    public List<CustomerReservationResponseDto> selectListReservation() {

        /*
        n+1 문제 발생 가능 어차피 예약을 만드는 주체는 RequestMapping("OWNER") 이니까 아래 findAll()로 가져와도 무방

        List<User> ownerList = authRepository.findByUserRole(UserRole.OWNER);

        List<Reservation> reservationList2 = new ArrayList<>();

        for (User owner : ownerList) {
            List<Reservation> reservations = reservationRepository.findByUser_Id(owner.getId());

            reservationList2.addAll(reservations);
        }
        */

        List<Reservation> reservationList = reservationRepository.findAllFetchJoin(UserRole.OWNER);

        List<CustomerReservationResponseDto> reservationResponseDtos = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            CustomerReservationResponseDto customerReservationResponseDto =
                    new CustomerReservationResponseDto(
                            reservation.getId(),
                            reservation.getName(),
                            reservation.getUser().getId(),
                            reservation.getMatchSeat().getId(),
                            reservation.getCost(),
                            reservation.isReserved()
                    );

            reservationResponseDtos.add(customerReservationResponseDto);
        }

        return reservationResponseDtos;
    }

    @Transactional
    public CustomerReservationResponseDto createCustomerReservation(Long reservationId, Long userCouponId, Long userId) {

        Reservation reservation = reservationRepository.findByIdWithMatchSeat(reservationId)
                .orElseThrow(() -> new BaseException(ErrorCode.RESERVATION_NOT_FOUND));


        UserCoupon userCoupon = null;

        Long cost = reservation.getCost();

        if (userCouponId != null) {
            userCoupon = userCouponRepository.findByIdAndUser_Id(userCouponId, userId)
                    .orElseThrow(() -> new BaseException(ErrorCode.USER_COUPON_NOT_FOUND));

            if (userCoupon.isUsed() == true) {
                throw new BaseException(ErrorCode.USER_COUPON_NOT_FOUND);
            }
            Long discountRate = userCoupon.getCoupon().getDiscountRate();

            cost = reservation.getCost() * (100 - discountRate) / 100;

            userCoupon.updateUserCoupon(true);
        }

        Long capacity = reservation.getMatchSeat().getCapacity();

        if (capacity <= 0) {
            throw new BaseException(ErrorCode.RESERVATION_FAILED);
        } else {
            reservation.getMatchSeat().setCapacity(capacity - 1);
        }

        /*User user = authRepository.findById(userId)
                        .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        reservation.setCustomer(user);
        reservation.setReserved(true);*/

        User customer = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Reservation customerReservation = Reservation.createCustomer(
                reservation.getName(),
                customer,
                reservation.getMatchSeat(),
                cost,
                true
        );

        reservationRepository.save(customerReservation);

        return  new CustomerReservationResponseDto(
                customerReservation.getId(),
                customerReservation.getName(),
                customerReservation.getUser().getId(),
                customerReservation.getMatchSeat().getId(),
                customerReservation.getCost(),
                customerReservation.isReserved()
        );
    }

    public List<CustomerReservationResponseDto> selectListCustomerReservation(Long userId) {

        List<Reservation> reservations = reservationRepository.findAllFetchJoinCustomer(userId, UserRole.CUSTOMER);

        List<CustomerReservationResponseDto> responseDtos = new ArrayList<>();

        if(reservations == null) {
            throw new BaseException(ErrorCode.RESERVATION_NOT_FOUND);
        }

        for(Reservation reservation : reservations) {
            CustomerReservationResponseDto response = new CustomerReservationResponseDto(
                    reservation.getId(),
                    reservation.getName(),
                    reservation.getUser().getId(),
                    reservation.getMatchSeat().getId(),
                    reservation.getCost(),
                    reservation.isReserved()
            );
            responseDtos.add(response);
        }

        return responseDtos;
    }
}
