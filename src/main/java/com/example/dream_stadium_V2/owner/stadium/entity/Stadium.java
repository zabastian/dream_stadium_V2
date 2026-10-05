package com.example.dream_stadium_V2.owner.stadium.entity;

import com.example.dream_stadium_V2.common.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stadium_id")
    private Long id;

    @Column(name = "stadium_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Stadium create(String name, User user) {
        Stadium stadium = new Stadium();
        stadium.name = name;
        stadium.user = user;
        return stadium;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
