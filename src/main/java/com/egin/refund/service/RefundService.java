package com.egin.refund.service;

import com.egin.common.model.CustomPage;
import com.egin.refund.model.Refund;
import com.egin.refund.model.dto.request.RefundByCashierAndDateRangePagingRequest;
import com.egin.refund.model.dto.request.RefundCreateRequest;
import com.egin.refund.model.dto.request.RefundPagingRequest;

import java.time.LocalDateTime;

public interface RefundService {

    Refund createRefund(final RefundCreateRequest refundCreateRequest);
    CustomPage<Refund> getAllRefunds(final RefundPagingRequest refundPagingRequest);
    Refund getRefundByCashierId(final String cashierId);
    Refund getRefundById(final String refundId);
    CustomPage<Refund> getRefundByBranchId(final String branchId, final RefundPagingRequest pagingRequest);
    CustomPage<Refund> getRefundByCashierAndDateRange(final RefundByCashierAndDateRangePagingRequest pagingRequest);
    void deleteRefund(final String refundId);

    // Shift report calculations
    Double calculateTotalRefundsByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate);

}
