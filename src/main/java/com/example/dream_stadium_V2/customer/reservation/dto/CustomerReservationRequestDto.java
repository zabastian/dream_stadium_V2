package com.example.dream_stadium_V2.customer.reservation.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CustomerReservationRequestDto {

        @NotNull
        private Long UserCouponId;

}
