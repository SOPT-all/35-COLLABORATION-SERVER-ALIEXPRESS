package com.example.aliexpress.common.message;

import org.springframework.http.HttpStatus;

public enum ProductErrorMessage implements DefaultErrorMessage {

    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다.");

    private HttpStatus httpStatus;
    private String message;

    ProductErrorMessage(HttpStatus httpStatus, String message) {
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
