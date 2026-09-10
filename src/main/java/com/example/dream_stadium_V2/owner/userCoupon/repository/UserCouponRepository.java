package com.example.dream_stadium_V2.owner.userCoupon.repository;

import com.example.dream_stadium_V2.owner.userCoupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    List<UserCoupon> findByUser_Id(Long userId);
}
