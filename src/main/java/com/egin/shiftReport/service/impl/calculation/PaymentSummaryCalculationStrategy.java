package com.egin.shiftReport.service.impl.calculation;

import com.egin.order.model.enums.PaymentType;
import com.egin.order.service.OrderService;
import com.egin.shiftReport.model.PaymentSummary;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Order(6)
@RequiredArgsConstructor
public class PaymentSummaryCalculationStrategy implements ShiftCalculationStrategy {

    private final OrderService orderService;

    @Override
    public void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        Map<PaymentType, Double> paymentTotals = orderService.getPaymentSummaryByCashierAndDateRange(
                cashierId, shiftStart, shiftEnd
        );

        List<PaymentSummary> paymentSummaries = new ArrayList<>();

        if (paymentTotals != null && !paymentTotals.isEmpty()) {
            for (Map.Entry<PaymentType, Double> entry : paymentTotals.entrySet()) {
                PaymentSummary summary = PaymentSummary.builder()
                        .paymentType(entry.getKey())
                        .totalAmount(entry.getValue())
                        .build();
                paymentSummaries.add(summary);
            }
        }

        shiftReportEntity.setPaymentSummaries(paymentSummaries);
    }

}

