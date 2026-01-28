package com.egin.order.repository;

import com.egin.order.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

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

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o WHERE o.cashier.id = :cashierId AND o.createdAt >= :startDate AND o.createdAt <= :endDate")
    Double calculateTotalSalesByCashierAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.cashier.id = :cashierId AND o.createdAt >= :startDate AND o.createdAt <= :endDate")
    Long countOrdersByCashierAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT o FROM OrderEntity o WHERE o.cashier.id = :cashierId AND o.createdAt >= :startDate AND o.createdAt <= :endDate ORDER BY o.createdAt DESC")
    List<OrderEntity> findRecentOrdersByCashierAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    @Query("SELECT o.paymentType, COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o WHERE o.cashier.id = :cashierId AND o.createdAt >= :startDate AND o.createdAt <= :endDate GROUP BY o.paymentType")
    List<Object[]> getPaymentSummaryByCashierAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
