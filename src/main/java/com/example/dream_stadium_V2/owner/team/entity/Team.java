package com.example.dream_stadium_V2.owner.team.entity;

import com.example.dream_stadium_V2.common.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Team create(User user, String name) {
        Team team = new Team();
        team.user = user;
        team.name = name;
        return team;
    }

    public void setName(String name) {
        this.name = name;
    }

}
