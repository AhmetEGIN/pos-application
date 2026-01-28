package com.egin.shiftReport.service;

import com.egin.shiftReport.model.entity.ShiftReportEntity;

import java.time.LocalDateTime;

/**
 * Strategy interface for calculating shift report metrics.
 * Follows Open/Closed Principle - new calculation strategies can be added without modifying existing code.
 */
public interface ShiftCalculationStrategy {

    /**
     * Calculates and updates the shift report entity with relevant metrics.
     *
     * @param shiftReportEntity The shift report entity to update
     * @param cashierId The cashier's ID
     * @param shiftStart The shift start time
     * @param shiftEnd The shift end time
     */
    void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd);

}

