package com.example.dream_stadium_V2.owner.userCoupon.entity;

import com.example.dream_stadium_V2.common.user.baseentity.BaseEntity;
import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.owner.coupon.entity.Coupon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_coupon_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    /*
    @Column(name = "name")
    private String name;
    */

    @Column(name = "is_used")
    private boolean isUsed;

    public static UserCoupon create(User user, Coupon coupon, boolean isUsed) {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.user = user;
        userCoupon.coupon = coupon;
        userCoupon.isUsed = isUsed;
        return userCoupon;
    }

    public void updateUserCoupon(boolean isUsed) {
        this.isUsed = isUsed;
    }


}
