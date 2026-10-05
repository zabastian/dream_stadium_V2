package com.example.dream_stadium_V2.common.auth.recaptcha;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecaptchaResponse {

    private boolean success;

    private double score;

    private String action;

    @JsonProperty("error-codes")
    private List<String> errorCodes;
}