package com.app.quantitymeasurement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/**
 * Entity saved in database
 */
@Entity
@Table(name = "quantity_measurement")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double thisValue;
    private double thatValue;
    private String operation;

    private double resultValue;

    private boolean error;
    private String errorMessage;

    private LocalDateTime createdAt;

    /**
     * Automatically set time before saving
     */
    @PrePersist
    public void setTime() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters

    public void setThisValue(double thisValue) {
        this.thisValue = thisValue;
    }
    public void setThatValue(double thatValue) {
        this.thatValue = thatValue;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }
    public void setError(boolean error) {
        this.error = error;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}