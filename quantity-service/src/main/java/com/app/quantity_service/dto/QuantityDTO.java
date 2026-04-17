package com.app.quantity_service.dto;

public class QuantityDTO {
    private double value;
    private String unit;
    private String measurementType; // Must match the switch case in getUnit()

    // Getters and Setters
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
}