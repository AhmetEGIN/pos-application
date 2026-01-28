package com.egin.shiftReport.service;

import com.egin.shiftReport.model.entity.ShiftReportEntity;

import java.time.LocalDateTime;

public interface ShiftCalculationStrategy {

    void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd);

}

