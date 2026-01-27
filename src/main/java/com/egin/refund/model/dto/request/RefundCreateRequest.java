package com.egin.refund.model.dto.request;

import com.egin.order.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundCreateRequest {

    private String orderId;
    private String branchId;
    private String reason;
    private Double amount;
    private PaymentType paymentType;
    // private String shiftReportId;

}
