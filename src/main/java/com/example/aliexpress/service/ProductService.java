package com.example.aliexpress.service;

import com.example.aliexpress.common.exception.BusinessException;
import com.example.aliexpress.common.message.ProductErrorMessage;
import com.example.aliexpress.dto.response.Product;
import com.example.aliexpress.dto.response.ProductList;
import com.example.aliexpress.dto.response.Review;
import com.example.aliexpress.dto.response.ReviewList;
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

    // 상품 정보 조회
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

    // 리뷰 조회 (긍정/부정)
    public ReviewList getProductReviews(Long productId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findByProduct_ProductId(productId);

        List<Review> goodReviews = reviewEntityList.stream()
                .filter(review -> review.getRating() >= 3)
                .map(this::convertToReview)
                .collect(Collectors.toList());

        List<Review> badReviews = reviewEntityList.stream()
                .filter(review -> review.getRating() <= 2)
                .map(this::convertToReview)
                .collect(Collectors.toList());

        return new ReviewList(goodReviews, badReviews);
    }

    private Review convertToReview(ReviewEntity reviewEntity) {
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
  
    public ProductEntity findByProductId(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ProductErrorMessage.NOT_FOUND_ERROR));
    }

    // 연관 상품 조회
    public ProductList getRelatedProducts(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ProductErrorMessage.NOT_FOUND_ERROR));

        Long categoryId = productEntity.getCategory().getCategoryId();
        List<ProductEntity> productEntityList = productRepository.findByCategory_CategoryId(categoryId);

        List<Product> relatedProducts = productEntityList.stream()
                .filter(p -> !p.getProductId().equals(productId))
                .map(this::convertToProduct)
                .collect(Collectors.toList());

        return new ProductList(relatedProducts);
    }

    private Product convertToProduct(ProductEntity productEntity) {
        Long productId = productEntity.getProductId();

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
  
}
