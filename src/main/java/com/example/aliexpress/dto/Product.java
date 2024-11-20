package com.example.aliexpress.dto;

public record Product(long productId, String productImage, String detail, double priceOriginal, int percent, double priceDiscount, boolean isCoupon, String categoryName, double reviewCount, double rating) {
}
