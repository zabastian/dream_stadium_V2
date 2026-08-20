package com.example.dream_stadium_V2.owner.stadium.repository;

import com.example.dream_stadium_V2.common.user.entity.User;
import com.example.dream_stadium_V2.owner.stadium.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {

    Optional<Stadium> findById(Long id);

    List<Stadium> findAllByUser(User user);

}
