package com.egin.payment.model.dto.response;

import com.egin.order.model.enums.PaymentType;
import com.egin.payment.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private String id;
    private String idempotencyKey;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private String orderId;
    private String branchId;
    private String cashierId;
    private String externalReferenceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String message;


    public static PaymentResponse success(String id, String idempotencyKey, BigDecimal amount,
                                           String currency, PaymentType paymentType,
                                           String orderId, String branchId, String cashierId,
                                           LocalDateTime createdAt) {
        return PaymentResponse.builder()
                .id(id)
                .idempotencyKey(idempotencyKey)
                .amount(amount)
                .currency(currency)
                .paymentStatus(PaymentStatus.PAID)
                .paymentType(paymentType)
                .orderId(orderId)
                .branchId(branchId)
                .cashierId(cashierId)
                .createdAt(createdAt)
                .message("Payment completed successfully")
                .build();
    }

    public static PaymentResponse failed(String id, String idempotencyKey) {
        return PaymentResponse.builder()
                .id(id)
                .idempotencyKey(idempotencyKey)
                .paymentStatus(PaymentStatus.FAILED)
//                .errorMessage(errorMessage)
                .message("Payment failed")
                .build();
    }

    public static PaymentResponse alreadyProcessed(String id, PaymentStatus paymentStatus) {
        return PaymentResponse.builder()
                .id(id)
                .paymentStatus(paymentStatus)
                .message("Payment already processed with this idempotency key")
                .build();
    }


}
