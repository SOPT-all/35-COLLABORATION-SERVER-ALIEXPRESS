package com.example.aliexpress.service;

import com.example.aliexpress.dto.Product;
import com.example.aliexpress.repository.ProductEntity;
import com.example.aliexpress.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        return new Product(
                productEntity.getProductId(),
                productEntity.getProductImage(),
                productEntity.getDetail(),
                productEntity.getPriceOriginal(),
                productEntity.getPercent(),
                productEntity.getPriceDiscount(),
                productEntity.isCoupon(),
                productEntity.getCategory().getCategoryName()
                );
    }
}
