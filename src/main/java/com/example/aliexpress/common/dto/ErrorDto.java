package com.example.aliexpress.common.dto;

public record ErrorDto(
        String message
) {
    public static ErrorDto of(String message) {
        return new ErrorDto(message);
    }
}
