package com.egin.payment.service.strategy.impl;

import com.egin.order.model.enums.PaymentType;
import com.egin.payment.model.dto.request.PaymentCreateRequest;
import com.egin.payment.model.entity.PaymentEntity;
import com.egin.payment.service.strategy.PaymentResult;
import com.egin.payment.service.strategy.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CashPaymentStrategy implements PaymentStrategy {


    @Override
    public PaymentResult processPayment(PaymentEntity paymentEntity, PaymentCreateRequest paymentCreateRequest) {
        log.info("Processing CASH payment for amount: {}", paymentEntity.getAmount());
        // Nakit ödemeler genellikle anında onaylanır
        String receiptNumber = generateReceiptNumber();

        log.info("Cash payment processed. Receipt: {}", receiptNumber);
        return PaymentResult.builder()
                .success(true)
                .externalReferenceId(receiptNumber)
                .build();
    }

    @Override
    public PaymentResult processRefund(PaymentEntity paymentEntity) {
        log.info("Processing CASH refund for payment: {}", paymentEntity.getId());

        // Nakit iade - kasadan para verilir
        String refundReceiptNumber = "REFUND-" + generateReceiptNumber();

        log.info("Cash refund successful. Receipt: {}", refundReceiptNumber);

        return PaymentResult.success(refundReceiptNumber);
    }

    @Override
    public PaymentType getSupportedPaymentType() {
        return PaymentType.CASH;
    }

    private String generateReceiptNumber() {
        return "CASH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
