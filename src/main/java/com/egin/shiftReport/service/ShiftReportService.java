package com.egin.shiftReport.service;

import com.egin.common.model.CustomPage;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.dto.request.ShiftReportByCashierAndDateRequest;
import com.egin.shiftReport.model.dto.request.ShiftReportPagingRequest;

import java.time.LocalDateTime;

public interface ShiftReportService {

    ShiftReport startShift(String branchId, String cashierId, LocalDateTime shiftStartTime);
    ShiftReport endShift(String shiftReportId, LocalDateTime shiftEndTime);
    ShiftReport getShiftReportById(String shiftId);
    CustomPage<ShiftReport> getAllShiftReports(final ShiftReportPagingRequest pagingRequest);
    CustomPage<ShiftReport> getShiftReportsByBranchId(final String branchId, final ShiftReportPagingRequest pagingRequest);
    CustomPage<ShiftReport> getShiftReportsByCashierId(final String cashierId, final ShiftReportPagingRequest pagingRequest);
    ShiftReport getCurrentShiftProgress(String cashierId);
    ShiftReport getShiftByCashierAndDate(final String cashierId, final ShiftReportByCashierAndDateRequest pagingRequest);



}
