package com.example.aliexpress.service;

import com.example.aliexpress.common.exception.BusinessException;
import com.example.aliexpress.common.message.BusinessErrorMessage;
import com.example.aliexpress.common.message.ProductErrorMessage;
import com.example.aliexpress.dto.ProductInfo;
import com.example.aliexpress.repository.ProductEntity;
import com.example.aliexpress.repository.ProductRepository;
import com.example.aliexpress.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public ProductInfo getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ProductErrorMessage.NOT_FOUND_ERROR));

        long reviewCount = reviewRepository.countByProduct_ProductId(productId);
        Double averageRating = reviewRepository.findAverageRatingByProductId(productId);

        if (averageRating == null) {
            averageRating = 0.0;
        }

        return new ProductInfo(
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
