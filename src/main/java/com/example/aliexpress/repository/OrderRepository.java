package com.example.aliexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByProduct_ProductId(long productId);

    boolean existsByProduct_ProductId(long productId);
}
