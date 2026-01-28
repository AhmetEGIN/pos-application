package com.egin.shiftReport.service.impl.calculation;

import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShiftReportCalculator {

    private final List<ShiftCalculationStrategy> calculationStrategies;

    /**
     * Executes all registered calculation strategies in order.
     *
     * @param shiftReportEntity The shift report entity to update
     * @param cashierId The cashier's ID
     * @param shiftStart The shift start time
     * @param shiftEnd The shift end time
     */
    public void calculateAll(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        log.debug("Starting shift report calculations for cashier: {}", cashierId);

        for (ShiftCalculationStrategy strategy : calculationStrategies) {
            log.debug("Executing strategy: {}", strategy.getClass().getSimpleName());
            strategy.calculate(shiftReportEntity, cashierId, shiftStart, shiftEnd);
        }

        log.debug("Completed all shift report calculations. TotalSales: {}, TotalRefunds: {}, NetSales: {}",
                shiftReportEntity.getTotalSales(),
                shiftReportEntity.getTotalRefunds(),
                shiftReportEntity.getNetSales());
    }

}

