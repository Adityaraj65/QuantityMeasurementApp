package com.app.quantity_service.dto;

public class QuantityMeasurementDTO {
    private double thisValue;
    private double thatValue;
    private String measurementType;
    private String operation;
    private double resultValue;
    private boolean error;
    private String errorMessage;
    private String resultString;

    // Standard Getters and Setters for all fields above...
    public double getThisValue() { return thisValue; }
    public void setThisValue(double thisValue) { this.thisValue = thisValue; }
    public double getThatValue() { return thatValue; }
    public void setThatValue(double thatValue) { this.thatValue = thatValue; }
    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public double getResultValue() { return resultValue; }
    public void setResultValue(double resultValue) { this.resultValue = resultValue; }
    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }
}