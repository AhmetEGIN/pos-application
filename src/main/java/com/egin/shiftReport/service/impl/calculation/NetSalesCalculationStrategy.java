package com.egin.shiftReport.service.impl.calculation;

import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(3)
public class NetSalesCalculationStrategy implements ShiftCalculationStrategy {

    @Override
    public void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        Double totalSales = shiftReportEntity.getTotalSales() != null ? shiftReportEntity.getTotalSales() : 0.0;
        Double totalRefunds = shiftReportEntity.getTotalRefunds() != null ? shiftReportEntity.getTotalRefunds() : 0.0;

        double netSales = totalSales - totalRefunds;
        shiftReportEntity.setNetSales(netSales);
    }

}

