package com.example.aliexpress.dto.response;

public record Product(
        long productId,
        String productImage,
        String detail,
        int priceOriginal,
        int percent,
        int priceDiscount,
        boolean isCoupon,
        String categoryName,
        double reviewCount,
        double rating) {
}
