package com.egin.payment.service.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResult {

    private boolean success;
    private String externalReferenceId;  // Banka/Gateway referans no
    private String errorCode;
    private String errorMessage;
    private String authorizationCode;    // Yetkilendirme kodu (kredi kartı için)
    private String transactionId;        // İşlem ID


    public static PaymentResult success(final String externalReferenceId) {
        return PaymentResult.builder()
                .success(true)
                .externalReferenceId(externalReferenceId)
                .build();
    }

    public static PaymentResult success(final String externalReferenceId, final String authorizationCode) {
        return PaymentResult.builder()
                .success(true)
                .externalReferenceId(externalReferenceId)
                .authorizationCode(authorizationCode)
                .build();
    }

    public static PaymentResult failed(final String errorCode, final String errorMessage) {
        return PaymentResult.builder()
                .success(false)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }





}
