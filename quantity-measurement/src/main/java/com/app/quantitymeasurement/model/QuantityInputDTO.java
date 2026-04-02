package com.app.quantitymeasurement.model;

/**
 * Wrapper for two quantities (used in API request)
 */
public class QuantityInputDTO {

    private QuantityDTO thisQuantityDTO;
    private QuantityDTO thatQuantityDTO;

    public QuantityDTO getThisQuantityDTO() {
        return thisQuantityDTO;
    }

    public QuantityDTO getThatQuantityDTO() {
        return thatQuantityDTO;
    }
}