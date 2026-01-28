package com.egin.shiftReport.repository;

import com.egin.shiftReport.model.entity.ShiftReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReportEntity, String> {

    Page<ShiftReportEntity> findByBranchEntityId(String branchId, Pageable pageable);

    Page<ShiftReportEntity> findByCashierId(String cashierId, Pageable pageable);

    @Query("SELECT s FROM ShiftReportEntity s WHERE s.cashier.id = :cashierId AND s.shiftEnd IS NULL")
    Optional<ShiftReportEntity> findCurrentShiftByCashierId(@Param("cashierId") String cashierId);

    @Query("SELECT s FROM ShiftReportEntity s WHERE s.cashier.id = :cashierId AND s.shiftStart >= :startDate AND s.shiftStart <= :endDate")
    Optional<ShiftReportEntity> findByCashierIdAndDateRange(
            @Param("cashierId") String cashierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}

