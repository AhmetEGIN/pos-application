package com.egin.payment.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.payment.model.Payment;
import com.egin.payment.model.dto.request.PaymentByOrderPagingRequest;
import com.egin.payment.model.dto.request.PaymentCreateRequest;
import com.egin.payment.model.dto.request.PaymentRefundRequest;
import com.egin.payment.model.dto.response.PaymentResponse;
import com.egin.payment.model.mapper.PaymentCustomPageToCustomPagingResponseMapper;
import com.egin.payment.model.mapper.PaymentToPaymentResponseMapper;
import com.egin.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<PaymentResponse> createPayment(
            @RequestHeader(value = "Idempotency-Key") String idempotencyKey,
            @RequestBody @Valid final PaymentCreateRequest request
    ) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return CustomResponse.<PaymentResponse>builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .isSuccess(false)
                    .build();
        }

        Payment payment = paymentService.processPayment(idempotencyKey, request);
        PaymentResponse response = PaymentToPaymentResponseMapper.toResponse(payment);

        return CustomResponse.successOf(response);
    }


    @GetMapping("/{payment-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<PaymentResponse> getPaymentById(
            @PathVariable(value = "payment-id") final String paymentId
    ) {

        Payment payment = paymentService.getPaymentById(paymentId);
        PaymentResponse response = PaymentToPaymentResponseMapper.toResponse(payment);

        return CustomResponse.successOf(response);
    }


    @GetMapping("/idempotency-key/{idempotency-key}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<PaymentResponse> getPaymentByIdempotencyKey(
            @PathVariable(value = "idempotency-key") final String idempotencyKey
    ) {
        Payment payment = paymentService.getPaymentByIdempotencyKey(idempotencyKey);
        PaymentResponse response = PaymentToPaymentResponseMapper.toResponse(payment);

        return CustomResponse.successOf(response);
    }


    @GetMapping("/order/{order-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<CustomPagingResponse<PaymentResponse>> getPaymentsByOrderId(
            @PathVariable(value = "order-id") final String orderId,
            @RequestBody final PaymentByOrderPagingRequest request
    ) {

        final CustomPagingResponse<PaymentResponse> response = PaymentCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        paymentService.getPaymentsByOrderId(orderId, request)
                );

        return CustomResponse.successOf(response);
    }


    @PostMapping("/{payment-id}/cancel")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<PaymentResponse> cancelPayment(
            @PathVariable(value = "payment-id") final String paymentId
    ) {
        Payment payment = paymentService.cancelPayment(paymentId);
        PaymentResponse response = PaymentToPaymentResponseMapper.toResponse(payment);

        return CustomResponse.successOf(response);
    }


    @PostMapping("/{payment-id}/refund")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<PaymentResponse> refundPayment(
            @PathVariable(value = "payment-id") final String paymentId,
            @RequestBody @Valid final PaymentRefundRequest request
    ) {
        Payment payment = paymentService.refundPayment(paymentId, request);
        PaymentResponse response = PaymentToPaymentResponseMapper.toResponse(payment);

        return CustomResponse.successOf(response);
    }


    @GetMapping("/order/{order-id}/has-successful-payment")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'CASHIER')")
    public CustomResponse<Boolean> hasSuccessfulPayment(
            @PathVariable(value = "order-id") final String orderId
    ) {
        boolean hasPayment = paymentService.hasSuccessfulPayment(orderId);
        return CustomResponse.successOf(hasPayment);
     }

}
