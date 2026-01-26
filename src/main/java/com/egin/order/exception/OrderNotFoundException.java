package com.egin.order.exception;

import java.io.Serial;

public class OrderNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2849154940579456438L;

    private static final String DEFAULT_MESSAGE = "Order not found";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

}

