package com.example.dream_stadium_V2.common.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false)
    private LoginType loginType;

    public static User create(String email, String password, String nickname, UserRole userRole, LoginType loginType) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.nickname = nickname;
        user.deleted = false;
        user.userRole = userRole;
        user.loginType = loginType;
        return user;
    }

    public static User createNaverUser(String email, String nickname, UserRole userRole, LoginType loginType) {
        User user = new User();
        user.email = email;
        user.password = "";
        user.nickname = nickname;
        user.deleted = false;
        user.userRole = userRole;
        user.loginType = loginType;
        return user;

    }

  /*  public static User login(String email, String password) {
        return new User(email, password);
    }*/
}
