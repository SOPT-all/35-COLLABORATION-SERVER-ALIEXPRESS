package com.example.aliexpress.controller;

import com.example.aliexpress.common.dto.ResponseDto;
import com.example.aliexpress.dto.ProductInfo;
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

    @GetMapping("{productId}")
    public ResponseEntity<ResponseDto<ProductInfo>> getProduct(@PathVariable Long productId) {
        ProductInfo product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(product));
    }
}
