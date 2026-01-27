package com.egin.refund.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.refund.model.Refund;
import com.egin.refund.model.dto.request.RefundByCashierAndDateRangePagingRequest;
import com.egin.refund.model.dto.request.RefundCreateRequest;
import com.egin.refund.model.dto.request.RefundPagingRequest;
import com.egin.refund.model.mapper.RefundCustomPageToCustomPagingResponseMapper;
import com.egin.refund.service.RefundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/refunds")
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Refund> createRefund(
            @RequestBody @Valid final RefundCreateRequest request
    ) {
        final Refund refund = refundService.createRefund(request);
        return CustomResponse.successOf(refund);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<CustomPagingResponse<Refund>> getAllRefunds(
            @RequestBody @Valid final RefundPagingRequest request
    ) {
        final CustomPagingResponse<Refund> refunds = RefundCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        refundService.getAllRefunds(request)
                );
        return CustomResponse.successOf(refunds);
    }

    @GetMapping("/{refund-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Refund> getRefundById(
            @PathVariable(value = "refund-id") final String refundId
    ) {
        final Refund refund = refundService.getRefundById(refundId);
        return CustomResponse.successOf(refund);
    }

    @GetMapping("/branch/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Refund>> getRefundsByBranchId(
            @PathVariable(value = "branch-id") final String branchId,
            @RequestBody @Valid final RefundPagingRequest request
    ) {
        final CustomPagingResponse<Refund> refunds = RefundCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        refundService.getRefundByBranchId(branchId, request)
                );
        return CustomResponse.successOf(refunds);
    }

    @GetMapping("/cashier/{cashier-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Refund> getRefundByCashierId(
            @PathVariable(value = "cashier-id") final String cashierId
    ) {
        final Refund refund = refundService.getRefundByCashierId(cashierId);
        return CustomResponse.successOf(refund);
    }

    @GetMapping("/cashier/date-range")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Refund>> getRefundsByCashierAndDateRange(
            @RequestBody @Valid final RefundByCashierAndDateRangePagingRequest request
    ) {
        final CustomPagingResponse<Refund> refunds = RefundCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        refundService.getRefundByCashierAndDateRange(request)
                );
        return CustomResponse.successOf(refunds);
    }

    @DeleteMapping("/{refund-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteRefund(
            @PathVariable(value = "refund-id") final String refundId
    ) {
        refundService.deleteRefund(refundId);
        return CustomResponse.SUCCESS;
    }

}

