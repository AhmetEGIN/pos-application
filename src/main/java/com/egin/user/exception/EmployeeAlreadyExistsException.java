package com.egin.user.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Employee already exists";

    public EmployeeAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeAlreadyExistsException(final String message) {
        super(message);
    }
}

