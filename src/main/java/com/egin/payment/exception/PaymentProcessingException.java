package com.egin.payment.exception;

public class PaymentProcessingException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "An error occurred while processing the payment.";


    public PaymentProcessingException(String message) {
        super(message);
    }

    public PaymentProcessingException() {
        super(DEFAULT_MESSAGE);
    }
}
