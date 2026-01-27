package com.egin.refund.repository;

import com.egin.refund.model.entity.RefundEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RefundRepository extends JpaRepository<RefundEntity, String> {

    Page<RefundEntity> findByBranchEntityId(String branchId, Pageable pageable);

    Page<RefundEntity> findByCashierId(String cashierId, Pageable pageable);

    RefundEntity findFirstByCashierId(String cashierId);

    Page<RefundEntity> findByOrderEntityId(String orderId, Pageable pageable);

    @Query("SELECT r FROM RefundEntity r WHERE r.cashier.id = :cashierId AND r.createdAt >= :startDate AND r.createdAt <= :endDate")
    Page<RefundEntity> findByCashierIdAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    // @Query("SELECT r FROM RefundEntity r WHERE r.shiftReportEntity.id = :shiftReportId")
    // Page<RefundEntity> findByShiftReportId(@Param("shiftReportId") String shiftReportId, Pageable pageable);

}
