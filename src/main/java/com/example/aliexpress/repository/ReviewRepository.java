package com.example.aliexpress.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    long countByProduct_ProductId(Long productId);

    @Query("SELECT AVG(r.rating) FROM ReviewEntity r WHERE r.product.productId = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.product.productId = :productId ORDER BY r.usefulCount DESC, r.recommendCount DESC, r.likeCount DESC")
    List<ReviewEntity> findByProduct_ProductId(Long productId);
}
