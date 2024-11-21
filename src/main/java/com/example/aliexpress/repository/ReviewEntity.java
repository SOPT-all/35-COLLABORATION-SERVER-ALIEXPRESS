package com.example.aliexpress.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String username;
    private int rating;
    private boolean isMonth;
    private String contentKorean;
    private String contentOriginal;
    private String reviewImage;
    private int usefulCount;
    private int recommendCount;
    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public ReviewEntity() {}

    public Long getReviewId() {
        return reviewId;
    }
    public String getUsername() {
        return username;
    }
    public int getRating() {
        return rating;
    }
    public boolean isMonth() {
        return isMonth;
    }
    public String getContentKorean() {
        return contentKorean;
    }
    public String getContentOriginal() {
        return contentOriginal;
    }
    public String getReviewImage() {
        return reviewImage;
    }
    public int getUsefulCount() {
        return usefulCount;
    }
    public int getRecommendCount() {
        return recommendCount;
    }
    public int getLikeCount() {
        return likeCount;
    }
    public ProductEntity getProduct() {
        return product;
    }
}
