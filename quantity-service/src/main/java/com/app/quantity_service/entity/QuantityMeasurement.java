package com.app.quantity_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    // Use @Column to map to a different name if you want, 
    // or just rename the fields to avoid reserved keywords
    private double valOne; 
    private String unitOne;
    private double valTwo;
    private String unitTwo;
    private double calculationResult;
    private String targetUnit;
    private String operation;
    private String measurementType;
    private String status; 

    @Column(name = "created_at")
    private LocalDateTime timestamp;

    public QuantityMeasurement() {
        this.timestamp = LocalDateTime.now();
    }

    // Update your Getters and Setters to match 'valOne', 'unitOne', etc.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public double getValOne() { return valOne; }
    public void setValOne(double valOne) { this.valOne = valOne; }
    public String getUnitOne() { return unitOne; }
    public void setUnitOne(String unitOne) { this.unitOne = unitOne; }
    public double getValTwo() { return valTwo; }
    public void setValTwo(double valTwo) { this.valTwo = valTwo; }
    public String getUnitTwo() { return unitTwo; }
    public void setUnitTwo(String unitTwo) { this.unitTwo = unitTwo; }
    public double getCalculationResult() { return calculationResult; }
    public void setCalculationResult(double calculationResult) { this.calculationResult = calculationResult; }
    public String getTargetUnit() { return targetUnit; }
    public void setTargetUnit(String targetUnit) { this.targetUnit = targetUnit; }
    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getTimestamp() { return timestamp; }
}