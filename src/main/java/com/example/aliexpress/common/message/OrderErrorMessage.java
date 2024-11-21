package com.example.aliexpress.common.message;

import org.springframework.http.HttpStatus;

public enum OrderErrorMessage implements DefaultErrorMessage {

    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다.");

    private HttpStatus httpStatus;
    private String message;

    OrderErrorMessage(HttpStatus httpStatus, String message) {
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
