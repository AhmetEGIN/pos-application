package com.egin.payment.exception;

public class PaymentAlreadyProcessedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Payment has already been processed.";

    public PaymentAlreadyProcessedException(String message) {
        super(message);
    }

    public PaymentAlreadyProcessedException() {
        super(DEFAULT_MESSAGE);
    }
}
