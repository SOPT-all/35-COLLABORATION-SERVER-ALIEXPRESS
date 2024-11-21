package com.example.aliexpress.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public OrderEntity() {}

    public OrderEntity(ProductEntity product) {
        this.quantity = 1;
        this.product = product;
    }

    public long getOrderId() {
        return orderId;
    }
    public int getQuantity() {
        return quantity;
    }
    public ProductEntity getProduct() {
        return product;
    }

    public void updateQuantity() {
        this.quantity++;
    }
}
