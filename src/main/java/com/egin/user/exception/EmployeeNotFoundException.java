package com.egin.user.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Employee not found";

    public EmployeeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeNotFoundException(final String message) {
        super(message);
    }
}

