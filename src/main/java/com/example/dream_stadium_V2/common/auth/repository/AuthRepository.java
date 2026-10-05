package com.example.dream_stadium_V2.common.auth.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.common.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByUserRole(UserRole userRole);
}
