package com.app.quantity_service.model;

import com.app.quantity_service.dto.QuantityDTO; // Assuming your First/Second quantities use this

public class QuantityInputDTO {
    private QuantityDTO firstQuantity;
    private QuantityDTO secondQuantity;
    private String targetUnit;

    // Standard Getters and Setters
    public QuantityDTO getFirstQuantity() { return firstQuantity; }
    public void setFirstQuantity(QuantityDTO firstQuantity) { this.firstQuantity = firstQuantity; }
    public QuantityDTO getSecondQuantity() { return secondQuantity; }
    public void setSecondQuantity(QuantityDTO secondQuantity) { this.secondQuantity = secondQuantity; }
    public String getTargetUnit() { return targetUnit; }
    public void setTargetUnit(String targetUnit) { this.targetUnit = targetUnit; }
}
