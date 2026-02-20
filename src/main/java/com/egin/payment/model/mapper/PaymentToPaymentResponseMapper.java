package com.egin.payment.model.mapper;

import com.egin.payment.model.Payment;
import com.egin.payment.model.dto.response.PaymentResponse;
import com.egin.payment.model.enums.PaymentStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentToPaymentResponseMapper {


    public PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .idempotencyKey(payment.getIdempotencyKey())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .paymentStatus(payment.getStatus())
                .paymentType(payment.getPaymentType())
                .orderId(payment.getOrder() != null ? payment.getOrder().getId() : null)
                .branchId(payment.getBranch() != null ? payment.getBranch().getId() : null)
                .cashierId(payment.getCashier() != null ? payment.getCashier().getId() : null)
                .externalReferenceId(payment.getExternalReferenceId())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .message(buildMessageFromStatus(payment.getStatus()))
                .build();
    }

    private String buildMessageFromStatus(PaymentStatus status) {
        if (status == null) {
            return "Payment status unknown";
        }
        return switch (status) {
            case PAID -> "Payment completed successfully";

            case CREATED -> "Payment has been created and is pending processing";
            case PROCESSING -> "Payment is currently being processed";
            case FAILED -> "Payment failed";
            case REFUNDED -> "Payment has been refunded";
            case CANCELED -> "Payment has been canceled";
            default -> "Payment status: " + status.name();
        };
    }
}
