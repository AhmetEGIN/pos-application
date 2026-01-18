package com.egin.product.exception.product;

public class ProductNotFoundException extends RuntimeException {

    private final static String DEFAULT_MESSAGE = "Product not found";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public ProductNotFoundException(String message) {
        super(message);
    }

}
