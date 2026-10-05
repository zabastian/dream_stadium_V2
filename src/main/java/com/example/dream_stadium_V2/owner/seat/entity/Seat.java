package com.example.dream_stadium_V2.owner.seat.entity;

import com.example.dream_stadium_V2.common.user.baseentity.BaseEntity;
import com.example.dream_stadium_V2.owner.matchSeat.entity.MatchSeat;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CollectionId;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "seats")
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    public static Seat create(String name) {
        Seat seat = new Seat();
        seat.name = name;
        return seat;
    }

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private List<MatchSeat> matchSeats = new ArrayList<>();
}
