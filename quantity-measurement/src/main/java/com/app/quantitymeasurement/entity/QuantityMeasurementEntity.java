package com.app.quantitymeasurement.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String result;
    private String error;
    private String measurementType;

    public QuantityMeasurementEntity(String operation, String result,
                                     String error, String measurementType) {
        this.operation = operation;
        this.result = result;
        this.error = error;
        this.measurementType = measurementType;
    }

    public String getOperation() { return operation; }
    public String getResult() { return result; }
    public String getError() { return error; }
    public String getMeasurementType() { return measurementType; }
}