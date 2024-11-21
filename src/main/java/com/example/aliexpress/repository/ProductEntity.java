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
    private int priceOriginal;
    private int percent;
    private int priceDiscount;
    private boolean isCoupon;

    @ManyToOne(fetch = FetchType.LAZY)
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
    public int getPriceOriginal() {
        return priceOriginal;
    }
    public int getPercent() {
        return percent;
    }
    public int getPriceDiscount() {
        return priceDiscount;
    }
    public boolean isCoupon() {
        return isCoupon;
    }
    public CategoryEntity getCategory() {
        return category;
    }
}
