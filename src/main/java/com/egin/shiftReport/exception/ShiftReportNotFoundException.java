package com.egin.shiftReport.exception;

import java.io.Serial;

public class ShiftReportNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7583926584926584123L;

    private static final String DEFAULT_MESSAGE = "Shift report not found";

    public ShiftReportNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ShiftReportNotFoundException(String message) {
        super(message);
    }

}

