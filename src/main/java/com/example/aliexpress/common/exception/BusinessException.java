package com.example.aliexpress.common.exception;

import com.example.aliexpress.common.message.DefaultErrorMessage;

public class BusinessException extends RuntimeException {
    private final DefaultErrorMessage errorMessage;

    public BusinessException(DefaultErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DefaultErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
