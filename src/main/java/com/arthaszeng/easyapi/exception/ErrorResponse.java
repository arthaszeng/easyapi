package com.arthaszeng.easyapi.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private Throwable exception;
    private String details;

    public ErrorResponse(Throwable exception) {
        this.exception = exception;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
