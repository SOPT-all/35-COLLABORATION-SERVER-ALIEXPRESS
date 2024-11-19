package com.example.aliexpress.common.message;

import org.springframework.http.HttpStatus;

public enum BusinessErrorMessage implements DefaultErrorMessage {
    //400 BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISSING_REQUIRED_HEADER(HttpStatus.BAD_REQUEST, "필수 헤더가 누락되었습니다."),
    // 500 INTERNAL_SEVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    ;

    private HttpStatus httpStatus;
    private String message;

    BusinessErrorMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}