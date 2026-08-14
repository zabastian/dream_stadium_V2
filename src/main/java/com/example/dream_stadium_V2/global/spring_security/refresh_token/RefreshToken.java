package com.example.dream_stadium_V2.global.spring_security.refresh_token;

import com.example.dream_stadium_V2.common.user.entity.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class RefreshToken {

    @Id
    private String token;

    private Long userId;

    private UserRole userRole;

    private Instant expiryDate;

    public RefreshToken() {}

    public RefreshToken(String token, Long userId, UserRole userRole, Instant expiryDate) {
        this.token = token;
        this.userId = userId;
        this.userRole = userRole;
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expiryDate.isBefore(Instant.now());
    }
    //만료일이 지금 시점보다 전인지 아닌지 확인하는 로직


}
