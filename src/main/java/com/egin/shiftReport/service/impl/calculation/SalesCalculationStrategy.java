package com.egin.shiftReport.service.impl.calculation;

import com.egin.order.service.OrderService;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Order(1)
@RequiredArgsConstructor
public class SalesCalculationStrategy implements ShiftCalculationStrategy {

    private static final Double DEFAULT_SALES_AMOUNT = 0.0;
    private static final Long DEFAULT_ORDER_COUNT = 0L;

    private final OrderService orderService;

    @Override
    public void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        // Calculate total sales
        Double totalSales = Optional.ofNullable(
                orderService.calculateTotalSalesByCashierAndDateRange(cashierId, shiftStart, shiftEnd)
        ).orElse(DEFAULT_SALES_AMOUNT);
        shiftReportEntity.setTotalSales(totalSales);

        // Calculate order count
        Long orderCount = Optional.ofNullable(
                orderService.countOrdersByCashierAndDateRange(cashierId, shiftStart, shiftEnd)
        ).orElse(DEFAULT_ORDER_COUNT);
        shiftReportEntity.setTotalOrders(orderCount.doubleValue());
    }

}

