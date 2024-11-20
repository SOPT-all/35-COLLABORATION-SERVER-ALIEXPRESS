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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public ReviewEntity() {}
}
