package com.example.aliexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    long countByProduct_ProductId(Long productId);

    @Query("SELECT AVG(r.rating) FROM ReviewEntity r WHERE r.product.productId = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);
}
