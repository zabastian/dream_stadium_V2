package com.example.dream_stadium_V2.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, 1),
    LOGIN_FAILED("로그인에 실패했습니다.", HttpStatus.UNAUTHORIZED, 2),
    TOKEN_IS_EXPIRED("토큰이 이미 만료되었습니다..", HttpStatus.UNAUTHORIZED, 3),
    RECAPTCHA_FAILED("구글 검증 로직에 걸렸습니다.", HttpStatus.UNAUTHORIZED,4),
    STADIUM_NOT_FOUND("해당 경기장을 찾을 수 없습니다.", HttpStatus.NOT_FOUND,5),
    TEAM_NOT_FOUND("해당 팀을 찾을 수 없습니다.", HttpStatus.NOT_FOUND,6),
    MATCH_NOT_FOUND("해당 경기를 찾을 수 없습니다.", HttpStatus.NOT_FOUND,7),
    SEAT_NOT_FOUND("해당 좌석을 찾을 수 없습니다.", HttpStatus.NOT_FOUND,8);

    private final String message;
    private final HttpStatus httpStatus;
    private final Integer ErrorNumber;

    ErrorCode(String message, HttpStatus httpStatus, Integer ErrorNumber) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.ErrorNumber = ErrorNumber;
    }
}
