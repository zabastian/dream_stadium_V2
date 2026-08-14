package com.example.dream_stadium_V2.global.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ErrorResponse {
    private final String error;
    private final String message;
    private final int status;
    private final int errorNumber;
    public final LocalDateTime time;

    public static ErrorResponse errorResponse(String error, String message, int status, int errorNumber, LocalDateTime time) {
        return new ErrorResponse(error,message,status,errorNumber,time);

    }
}
