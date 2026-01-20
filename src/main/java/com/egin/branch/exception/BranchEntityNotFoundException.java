package com.egin.branch.exception;

public class BranchEntityNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Branch not found";

    public BranchEntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public BranchEntityNotFoundException(String message) {
        super(message);
    }


}
