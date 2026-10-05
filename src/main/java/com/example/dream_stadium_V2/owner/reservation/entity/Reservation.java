package com.example.dream_stadium_V2.owner.reservation.entity;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import com.example.dream_stadium_V2.owner.userCoupon.entity.UserCoupon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_coupon_id")
    private UserCoupon userCoupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchSeat_id")
    private MatchSeat matchSeat;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "is_reserved")
    private boolean isReserved = false;

    @Column(name = "is_remained")
    private boolean isRemained = true;

    public static Reservation create(String name, User user, MatchSeat matchSeat, Long cost, boolean isReserved) {
        Reservation reservation = new Reservation();
        reservation.name = name;
        reservation.user = user;
        reservation.matchSeat = matchSeat;
        reservation.cost = cost;
        reservation.isReserved = isReserved;
        return reservation;
    }

    public static Reservation createCustomer(String name, User user, MatchSeat matchSeat, Long cost, boolean isReserved) {
        Reservation reservation = new Reservation();
        reservation.name = name;
        reservation.user = user;
        reservation.matchSeat = matchSeat;
        reservation.cost = cost;
        reservation.isReserved = isReserved;
        return reservation;
    }
}
