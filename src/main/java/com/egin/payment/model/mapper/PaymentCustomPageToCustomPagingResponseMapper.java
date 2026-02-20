package com.egin.payment.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.payment.model.Payment;
import com.egin.payment.model.dto.response.PaymentResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<PaymentResponse> toCustomPagingResponse(final CustomPage<Payment> paymentsByOrderId) {
        return CustomPagingResponse.<PaymentResponse>builder()
                .content(paymentsByOrderId.getContent().stream()
                        .map(PaymentToPaymentResponseMapper::toResponse)
                        .toList())
                .pageNumber(paymentsByOrderId.getPageNumber())
                .pageSize(paymentsByOrderId.getPageSize())
                .totalElementCount(paymentsByOrderId.getTotalElementCount())
                .totalPageCount(paymentsByOrderId.getTotalPageCount())
                .build();
    }
}
