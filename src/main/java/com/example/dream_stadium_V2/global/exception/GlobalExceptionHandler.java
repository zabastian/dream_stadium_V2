package com.example.dream_stadium_V2.global.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse errorResponse = ErrorResponse.errorResponse(
                e.getErrorCode().name(),
                e.getErrorCode().getMessage(),
                e.getErrorCode().getHttpStatus().value(),
                e.getErrorCode().getErrorNumber(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponse);
    }
}
