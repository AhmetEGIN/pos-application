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
public class BankTransferPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentEntity paymentEntity, PaymentCreateRequest paymentCreateRequest) {
        log.info("Processing BANK_TRANSFER payment for amount: {}", paymentEntity.getAmount());
        // Banka transferi için referans numarası oluştur
        // Gerçek sistemde bu numara müşteriye verilir ve banka ile eşleştirilir
        String referenceNumber = "BT-" + paymentEntity.getId().toString().substring(0,10).toUpperCase();
        log.info("Bank transfer initiated. Reference: {}", referenceNumber);

        return PaymentResult.success(referenceNumber);
    }

    @Override
    public PaymentResult processRefund(PaymentEntity paymentEntity) {
        log.info("Processing BANK_TRANSFER refund for payment: {}", paymentEntity.getId());

        // Örnek işlem
        String refundReferenceNumber = "REFUND-BT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        log.info("Bank transfer refund initiated. Reference: {}", refundReferenceNumber);

        return PaymentResult.success(refundReferenceNumber);
    }

    @Override
    public PaymentType getSupportedPaymentType() {
        return PaymentType.BANK_TRANSFER;
    }
}
