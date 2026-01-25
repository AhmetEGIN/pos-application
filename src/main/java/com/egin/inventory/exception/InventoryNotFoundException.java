package com.egin.inventory.exception;

public class InventoryNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Inventory not found.";

    public InventoryNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }
}
