package com.example.aliexpress.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productImage;
    private String detail;
    private double priceOriginal;
    private int percent;
    private double priceDiscount;
    private boolean isCoupon;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public ProductEntity() {}

    public Long getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }
    public String getDetail() {
        return detail;
    }
    public double getPriceOriginal() {
        return priceOriginal;
    }
    public int getPercent() {
        return percent;
    }
    public double getPriceDiscount() {
        return priceDiscount;
    }
    public boolean isCoupon() {
        return isCoupon;
    }
    public CategoryEntity getCategory() {
        return category;
    }
}
