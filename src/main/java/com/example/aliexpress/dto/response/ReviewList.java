package com.example.aliexpress.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReviewList {
    @JsonProperty
    private List<Review> goodReviews;
    @JsonProperty
    private List<Review> badReviews;

    public ReviewList(List<Review> goodReviews, List<Review> badReviews) {
        this.goodReviews = goodReviews;
        this.badReviews = badReviews;
    }
}
