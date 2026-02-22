package com.egin.shiftReport.service.impl.calculation;

import com.egin.order.model.entity.OrderEntity;
import com.egin.order.model.mapper.OrderToOrderEntityMapper;
import com.egin.order.service.OrderService;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(4)
@RequiredArgsConstructor
public class RecentOrdersCalculationStrategy implements ShiftCalculationStrategy {

    private static final int MAX_RECENT_ORDERS = 10;

    private final OrderService orderService;

    @Override
    public void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        List<com.egin.order.model.Order> recentOrders = orderService.getRecentOrdersByCashierAndDateRange(
                cashierId, shiftStart, shiftEnd, MAX_RECENT_ORDERS
        );

        if (recentOrders != null && !recentOrders.isEmpty()) {
            List<OrderEntity> orderEntities = recentOrders.stream()
                    .map(OrderToOrderEntityMapper::toOrderEntity)
                    .collect(Collectors.toList());
            shiftReportEntity.setRecentOrders(orderEntities);
        } else {
            shiftReportEntity.setRecentOrders(Collections.emptyList());
        }
    }

}

