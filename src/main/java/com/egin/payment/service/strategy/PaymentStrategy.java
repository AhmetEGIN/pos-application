package com.egin.payment.service.strategy;

import com.egin.order.model.enums.PaymentType;
import com.egin.payment.model.dto.request.PaymentCreateRequest;
import com.egin.payment.model.entity.PaymentEntity;

public interface PaymentStrategy {

    PaymentResult processPayment(final PaymentEntity paymentEntity, final PaymentCreateRequest paymentCreateRequest);

    PaymentResult processRefund(final PaymentEntity paymentEntity);

    PaymentType getSupportedPaymentType();



}
