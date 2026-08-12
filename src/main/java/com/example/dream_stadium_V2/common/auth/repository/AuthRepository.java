package com.example.dream_stadium_V2.common.auth.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {

}
