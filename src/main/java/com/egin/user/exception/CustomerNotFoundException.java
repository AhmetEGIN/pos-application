package com.egin.user.exception;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5403302048964270984L;

    private static final String DEFAULT_MESSAGE = "Customer not found";

    public CustomerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

}

