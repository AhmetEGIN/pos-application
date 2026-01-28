package com.egin.shiftReport.model;

import com.egin.order.model.enums.PaymentType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentSummary {

    private PaymentType paymentType;
    private Double totalAmount;
    private int transactionCount;
    private Double percentage;


}
