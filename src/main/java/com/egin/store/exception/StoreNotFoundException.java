package com.egin.store.exception;

public class StoreNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Store not found";

    public StoreNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public StoreNotFoundException(String message) {
        super(message);
    }
}
