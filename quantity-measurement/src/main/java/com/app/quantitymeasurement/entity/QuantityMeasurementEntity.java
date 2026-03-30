package com.app.quantitymeasurement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quantity_measurement")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private String result;
    private String error;
    private String measurementType;

    // Default constructor (MANDATORY for JPA)
    public QuantityMeasurementEntity() {}

    // Parameterized constructor
    public QuantityMeasurementEntity(String operation, String result,
                                     String error, String measurementType) {
        this.operation = operation;
        this.result = result;
        this.error = error;
        this.measurementType = measurementType;
    }

    // Getters
    public Long getId() { return id; }
    public String getOperation() { return operation; }
    public String getResult() { return result; }
    public String getError() { return error; }
    public String getMeasurementType() { return measurementType; }

    // Setters (IMPORTANT for JPA)
    public void setId(Long id) { this.id = id; }
    public void setOperation(String operation) { this.operation = operation; }
    public void setResult(String result) { this.result = result; }
    public void setError(String error) { this.error = error; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
}