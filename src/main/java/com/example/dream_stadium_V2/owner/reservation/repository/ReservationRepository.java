package com.example.dream_stadium_V2.owner.reservation.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import com.example.dream_stadium_V2.owner.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r JOIN FETCH r.user JOIN FETCH r.matchSeat WHERE r.user.userRole = :userRole")
    List<Reservation> findAllFetchJoin(@Param("userRole") UserRole userRole);

    @Query("select r from Reservation r JOIN FETCH r.user JOIN FETCH r.matchSeat WHERE r.user.id = :Ids AND r.user.userRole = :userRole")
    List<Reservation> findAllFetchJoinCustomer(@Param("Ids")Long userId, @Param("userRole") UserRole userRole);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.matchSeat JOIN FETCH r.user WHERE r.id = :reservationId")
    Optional<Reservation> findByIdWithMatchSeat(@Param("reservationId") Long reservationId);

    Optional<Reservation> findByIdAndUser_Id(Long reservationId, Long userId );

    Long user(User user);
}
