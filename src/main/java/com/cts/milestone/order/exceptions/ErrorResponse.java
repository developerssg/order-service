package com.cts.milestone.order.exceptions;

import java.util.List;

public class ErrorResponse {
    private String errorCode;
    private List<String> errorMessage;

    public ErrorResponse(String errorCode, List<String> errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
