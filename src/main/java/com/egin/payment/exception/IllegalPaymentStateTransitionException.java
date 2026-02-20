package com.egin.payment.exception;

import com.egin.payment.model.enums.PaymentStatus;

public class IllegalPaymentStateTransitionException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Illegal payment state transition.";

    public IllegalPaymentStateTransitionException(String message) {
        super(message);
    }

    public IllegalPaymentStateTransitionException() {
        super(DEFAULT_MESSAGE);
    }

    public IllegalPaymentStateTransitionException(PaymentStatus from, PaymentStatus to) {
        super(String.format("Invalid payment state transition from %s to %s", from, to));
    }
}
