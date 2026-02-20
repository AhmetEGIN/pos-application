package com.egin.payment.service;

import com.egin.common.model.CustomPage;
import com.egin.payment.model.Payment;
import com.egin.payment.model.dto.request.PaymentByOrderPagingRequest;
import com.egin.payment.model.dto.request.PaymentCreateRequest;
import com.egin.payment.model.dto.request.PaymentRefundRequest;

public interface PaymentService {


    Payment processPayment(final String idempotencyKey, final PaymentCreateRequest request);


    Payment getPaymentById(final String paymentId);

    Payment getPaymentByIdempotencyKey(final String idempotencyKey);


    CustomPage<Payment> getPaymentsByOrderId(final String orderId, final PaymentByOrderPagingRequest request);


    Payment cancelPayment(final String paymentId);


    Payment refundPayment(final String paymentId, final PaymentRefundRequest request);

    boolean hasSuccessfulPayment(final String orderId);
}
