package com.egin.product.repository;

import com.egin.product.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    Page<ProductEntity> findByStoreEntityId(String storeId, Pageable pageable);

    @Query(
            "SELECT p FROM ProductEntity p " +
                    "WHERE p.storeEntity.id = :storeId " +
                    "AND (" +
                    "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) "+
                    "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
                    "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
                    "OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
                    ")"
    )
    List<ProductEntity> searchByKeyword(
            @Param("store-id") String storeId,
            @Param("keyword") String keyword
    );

    @Query("SELECT oi.productEntity FROM OrderItemEntity oi " +
            "JOIN oi.orderEntity o " +
            "WHERE o.cashier.id = :cashierId " +
            "AND o.createdAt >= :startDate " +
            "AND o.createdAt <= :endDate " +
            "GROUP BY oi.productEntity " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<ProductEntity> findTopSellingProductsByCashierAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}


