package com.example.aliexpress.service;

import com.example.aliexpress.common.exception.BusinessException;
import com.example.aliexpress.common.message.OrderErrorMessage;
import com.example.aliexpress.repository.OrderEntity;
import com.example.aliexpress.repository.OrderRepository;
import com.example.aliexpress.repository.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Transactional
    // 처음 주문이면 주문 테이블에 저장, 이미 주문했으면 수량만 증가
    public void createOrder(long productId) {
        if (checkExist(productId)) {
            OrderEntity orderEntity = findByProductId(productId);
            orderEntity.updateQuantity();
        } else {
            ProductEntity productEntity = productService.findByProductId(productId);
            OrderEntity orderEntity = new OrderEntity(productEntity);
            orderRepository.save(orderEntity);
        }
    }

    private boolean checkExist(long productId) {
        return orderRepository.existsByProduct_ProductId(productId);
    }

    private OrderEntity findByProductId(long productId) {
        return orderRepository.findByProduct_ProductId(productId)
                .orElseThrow(() -> new BusinessException(OrderErrorMessage.NOT_FOUND_ERROR));
    }
}
