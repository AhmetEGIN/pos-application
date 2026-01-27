package com.egin.refund.exception;

import java.io.Serial;

public class RefundNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8374962547839562847L;

    private static final String DEFAULT_MESSAGE = "Refund not found";

    public RefundNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public RefundNotFoundException(String message) {
        super(message);
    }

}

