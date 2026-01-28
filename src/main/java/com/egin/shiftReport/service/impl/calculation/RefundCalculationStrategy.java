package com.egin.shiftReport.service.impl.calculation;

import com.egin.refund.service.RefundService;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Order(2)
@RequiredArgsConstructor
public class RefundCalculationStrategy implements ShiftCalculationStrategy {

    private static final Double DEFAULT_REFUND_AMOUNT = 0.0;

    private final RefundService refundService;

    @Override
    public void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        Double totalRefunds = Optional.ofNullable(
                refundService.calculateTotalRefundsByCashierAndDateRange(cashierId, shiftStart, shiftEnd)
        ).orElse(DEFAULT_REFUND_AMOUNT);
        shiftReportEntity.setTotalRefunds(totalRefunds);
    }

}

