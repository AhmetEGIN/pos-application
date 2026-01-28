package com.egin.shiftReport.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.dto.request.ShiftReportByCashierAndDateRequest;
import com.egin.shiftReport.model.dto.request.ShiftReportEndRequest;
import com.egin.shiftReport.model.dto.request.ShiftReportPagingRequest;
import com.egin.shiftReport.model.dto.request.ShiftReportStartRequest;
import com.egin.shiftReport.model.mapper.ShiftReportCustomPageToCustomPagingResponseMapper;
import com.egin.shiftReport.service.ShiftReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shift-reports")
@RequiredArgsConstructor
public class ShiftReportController {

    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<ShiftReport> startShift(
            @RequestBody @Valid final ShiftReportStartRequest request,
            @RequestParam(value = "cashier-id") final String cashierId
    ) {
        final ShiftReport shiftReport = shiftReportService.startShift(
                request.getBranchId(),
                cashierId,
                request.getShiftStartTime()
        );
        return CustomResponse.successOf(shiftReport);
    }

    @PostMapping("/end")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<ShiftReport> endShift(
            @RequestBody @Valid final ShiftReportEndRequest request
    ) {
        final ShiftReport shiftReport = shiftReportService.endShift(
                request.getShiftReportId(),
                request.getShiftEndTime()
        );
        return CustomResponse.successOf(shiftReport);
    }

    @GetMapping("/{shift-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<ShiftReport> getShiftReportById(
            @PathVariable(value = "shift-id") final String shiftId
    ) {
        final ShiftReport shiftReport = shiftReportService.getShiftReportById(shiftId);
        return CustomResponse.successOf(shiftReport);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<CustomPagingResponse<ShiftReport>> getAllShiftReports(
            @RequestBody @Valid final ShiftReportPagingRequest request
    ) {
        final CustomPagingResponse<ShiftReport> shiftReports = ShiftReportCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        shiftReportService.getAllShiftReports(request)
                );
        return CustomResponse.successOf(shiftReports);
    }

    @GetMapping("/branch/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<ShiftReport>> getShiftReportsByBranchId(
            @PathVariable(value = "branch-id") final String branchId,
            @RequestBody @Valid final ShiftReportPagingRequest request
    ) {
        final CustomPagingResponse<ShiftReport> shiftReports = ShiftReportCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        shiftReportService.getShiftReportsByBranchId(branchId, request)
                );
        return CustomResponse.successOf(shiftReports);
    }

    @GetMapping("/cashier/{cashier-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<ShiftReport>> getShiftReportsByCashierId(
            @PathVariable(value = "cashier-id") final String cashierId,
            @RequestBody @Valid final ShiftReportPagingRequest request
    ) {
        final CustomPagingResponse<ShiftReport> shiftReports = ShiftReportCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        shiftReportService.getShiftReportsByCashierId(cashierId, request)
                );
        return CustomResponse.successOf(shiftReports);
    }

    @GetMapping("/cashier/{cashier-id}/current")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<ShiftReport> getCurrentShiftProgress(
            @PathVariable(value = "cashier-id") final String cashierId
    ) {
        final ShiftReport shiftReport = shiftReportService.getCurrentShiftProgress(cashierId);
        return CustomResponse.successOf(shiftReport);
    }

    @GetMapping("/cashier/{cashier-id}/date")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<ShiftReport> getShiftByCashierAndDate(
            @PathVariable(value = "cashier-id") final String cashierId,
            @RequestBody @Valid final ShiftReportByCashierAndDateRequest request
    ) {
        final ShiftReport shiftReport = shiftReportService.getShiftByCashierAndDate(cashierId, request);
        return CustomResponse.successOf(shiftReport);
    }

}
