package com.example.aliexpress.dto;

public record Review(
        long reviewId,
        String username,
        int rating,
        boolean isMonth,
        String contentKorean,
        String contentOriginal,
        String reviewImage,
        int usefulCount,
        int recommendCount,
        int likeCount) {
}
