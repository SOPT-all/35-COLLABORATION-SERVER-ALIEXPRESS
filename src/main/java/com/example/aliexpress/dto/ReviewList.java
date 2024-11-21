package com.example.aliexpress.dto;

import java.util.List;

public class ReviewList {
    private List<Review> goodReviews;
    private List<Review> badReviews;

    public ReviewList(List<Review> goodReviews, List<Review> badReviews) {
        this.goodReviews = goodReviews;
        this.badReviews = badReviews;
    }

    public List<Review> getGoodReviews() {
        return goodReviews;
    }

    public List<Review> getBadReviews() {
        return badReviews;
    }
}
