package com.example.dream_stadium_V2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Auditing을 실제로 켜준다.
@SpringBootApplication
public class DreamStatdiumV2Application {

    public static void main(String[] args) {
        SpringApplication.run(DreamStatdiumV2Application.class, args);
    }

}
