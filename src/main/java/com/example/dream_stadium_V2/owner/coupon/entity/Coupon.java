package com.example.dream_stadium_V2.owner.coupon.entity;

import com.example.dream_stadium_V2.common.user.baseentity.BaseEntity;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.global.exception.BaseException;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import com.example.dream_stadium_V2.owner.userCoupon.entity.UserCoupon;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Coupon")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discount_rate")
    private Long discountRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Coupon create(String name, Long discountRate, User user) {
        Coupon coupon = new Coupon();
        coupon.name = name;
        coupon.discountRate = discountRate;
        coupon.user = user;
        return coupon;
    }

    public void update(String name, Long discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<UserCoupon> userCoupons = new ArrayList<>();
}
