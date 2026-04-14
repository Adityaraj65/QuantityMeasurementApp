package com.app.quantitymeasurement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double thisValue;
    private double thatValue;
    private String measurementType;
    private String operation;
    private double resultValue;

    // Force the column name to 'error' to match Hibernate's insert query
    @Column(name = "error", nullable = false)
    private boolean error = false; 

    private String errorMessage;
    private LocalDateTime createdAt;

    @PrePersist
    public void setTime() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}