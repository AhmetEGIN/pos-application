package com.egin.order.repository;

import com.egin.order.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    Page<OrderEntity> findByBranchEntityId(String branchId, Pageable pageable);

    Page<OrderEntity> findByCashierId(String cashierId, Pageable pageable);

    Page<OrderEntity> findByCustomerEntityId(String customerId, Pageable pageable);

    @Query("SELECT o FROM OrderEntity o WHERE o.branchEntity.id = :branchId AND o.createdAt >= :startOfDay AND o.createdAt < :endOfDay ORDER BY o.createdAt DESC")
    Page<OrderEntity> findTodayOrdersByBranchId(
            @Param("branchId") String branchId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay,
            Pageable pageable
    );

    @Query("SELECT o FROM OrderEntity o WHERE o.branchEntity.id = :branchId ORDER BY o.createdAt DESC")
    Page<OrderEntity> findTop5RecentOrdersByBranchId(@Param("branchId") String branchId, Pageable pageable);

}

