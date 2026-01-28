package com.egin.shiftReport.exception;

import java.io.Serial;

public class ShiftAlreadyActiveException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8473926584926584234L;

    private static final String DEFAULT_MESSAGE = "Cashier already has an active shift";

    public ShiftAlreadyActiveException() {
        super(DEFAULT_MESSAGE);
    }

    public ShiftAlreadyActiveException(String message) {
        super(message);
    }

}

