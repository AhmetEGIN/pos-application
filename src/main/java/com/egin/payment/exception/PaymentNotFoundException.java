package com.egin.payment.exception;

public class PaymentNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Payment not found";

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}

