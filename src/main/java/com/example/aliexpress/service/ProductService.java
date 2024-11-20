package com.example.aliexpress.service;

import com.example.aliexpress.common.exception.BusinessException;
import com.example.aliexpress.common.message.ProductErrorMessage;
import com.example.aliexpress.dto.Product;
import com.example.aliexpress.dto.Review;
import com.example.aliexpress.dto.ReviewList;
import com.example.aliexpress.repository.ProductEntity;
import com.example.aliexpress.repository.ProductRepository;
import com.example.aliexpress.repository.ReviewEntity;
import com.example.aliexpress.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public Product getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ProductErrorMessage.NOT_FOUND_ERROR));

        long reviewCount = reviewRepository.countByProduct_ProductId(productId);
        Double averageRating = reviewRepository.findAverageRatingByProductId(productId);

        if (averageRating == null) {
            averageRating = 0.0;
        }

        return new Product(
                productEntity.getProductId(),
                productEntity.getProductImage(),
                productEntity.getDetail(),
                productEntity.getPriceOriginal(),
                productEntity.getPercent(),
                productEntity.getPriceDiscount(),
                productEntity.isCoupon(),
                productEntity.getCategory().getCategoryName(),
                reviewCount,
                averageRating
        );
    }

    public ReviewList getProductReviews(Long productId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findByProduct_ProductId(productId);

        List<Review> goodReviews = reviewEntityList.stream()
                .filter(review -> review.getRating() >= 3)
                .map(this::convertToDto)
                .collect(Collectors.toList());

        List<Review> badReviews = reviewEntityList.stream()
                .filter(review -> review.getRating() <= 2)
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ReviewList(goodReviews, badReviews);
    }

    private Review convertToDto(ReviewEntity reviewEntity) {
        return new Review(
                reviewEntity.getReviewId(),
                reviewEntity.getUsername(),
                reviewEntity.getRating(),
                reviewEntity.isMonth(),
                reviewEntity.getContentKorean(),
                reviewEntity.getContentOriginal(),
                reviewEntity.getReviewImage(),
                reviewEntity.getUsefulCount(),
                reviewEntity.getRecommendCount(),
                reviewEntity.getLikeCount()
        );
    }
}
