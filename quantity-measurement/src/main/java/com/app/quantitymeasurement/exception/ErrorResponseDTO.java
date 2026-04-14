package com.app.quantitymeasurement.exception;

public class ErrorResponseDTO {
    private String errorMessage;
    private boolean error = true;

    public ErrorResponseDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    // Getters and Setters
    public String getErrorMessage() { return errorMessage; }
    public boolean isError() { return error; }
}