package com.example.aliexpress.dto.response;

import com.example.aliexpress.repository.OrderEntity;

public record OrderResponse(
        long productId,
        String productImage,
        String detail,
        int price,
        int quantity
) {
    public static OrderResponse of(OrderEntity orderEntity) {
        return new OrderResponse(
                orderEntity.getOrderId(),
                orderEntity.getProduct().getProductImage(),
                orderEntity.getProduct().getDetail(),
                orderEntity.getProduct().getPriceDiscount(),
                orderEntity.getQuantity()
        );
    }
}
