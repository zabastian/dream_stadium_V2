package com.example.dream_stadium_V2.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BaseException extends RuntimeException{
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
