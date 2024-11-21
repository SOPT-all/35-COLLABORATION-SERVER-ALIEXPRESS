package com.example.aliexpress.controller;

import com.example.aliexpress.common.dto.ResponseDto;
import com.example.aliexpress.dto.Order;
import com.example.aliexpress.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<ResponseDto<Void>> createOrder(
            @RequestBody Order order
    ) {
        orderService.createOrder(order.productId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.success(null));
    }
}
