package com.apps.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operation;
    private String result;
    private String error;

    public QuantityMeasurementEntity(String operation, String result) {
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(String operation, String result, String error) {
        this.operation = operation;
        this.result = result;
        this.error = error;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public String toString() {

        if (hasError()) {
            return "Operation=" + operation + " Error=" + error;
        }

        return "Operation=" + operation + " Result=" + result;
    }
}