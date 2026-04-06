package com.app.quantitymeasurement.entity;

import java.time.LocalDateTime;

import com.app.quantitymeasurement.model.OperationType;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurement")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double thisValue;
    private double thatValue;

    @Enumerated(EnumType.STRING)
    private OperationType operation;

    private double resultValue;

    private boolean error;
    private String errorMessage;

    private LocalDateTime createdAt;

    @PrePersist
    public void setTime() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public double getThisValue() { return thisValue; }
    public void setThisValue(double thisValue) { this.thisValue = thisValue; }

    public double getThatValue() { return thatValue; }
    public void setThatValue(double thatValue) { this.thatValue = thatValue; }

    public OperationType getOperation() { return operation; }
    public void setOperation(OperationType operation) { this.operation = operation; }

    public double getResultValue() { return resultValue; }
    public void setResultValue(double resultValue) { this.resultValue = resultValue; }

    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}