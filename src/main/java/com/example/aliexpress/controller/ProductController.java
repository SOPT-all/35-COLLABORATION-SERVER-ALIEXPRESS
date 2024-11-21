package com.example.aliexpress.controller;

import com.example.aliexpress.common.dto.ResponseDto;
import com.example.aliexpress.dto.response.Product;
import com.example.aliexpress.dto.response.ProductList;
import com.example.aliexpress.dto.response.ReviewList;
import com.example.aliexpress.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 정보 조회
    @GetMapping("{productId}")
    public ResponseEntity<ResponseDto<Product>> getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(product));
    }

    // 리뷰 조회 (긍정/부정)
    @GetMapping("{productId}/reviews")
    public ResponseEntity<ResponseDto<ReviewList>> getProductReviews(@PathVariable Long productId) {
        ReviewList reviewList= productService.getProductReviews(productId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(reviewList));
    }

    // 연관 상품 조회
    @GetMapping("{productId}/related")
    public ResponseEntity<ResponseDto<ProductList>> getRelatedProducts(@PathVariable Long productId) {
        ProductList productList = productService.getRelatedProducts(productId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(productList));
    }
}
