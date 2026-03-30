package com.apps.quantitymeasurement;

/**
 * LengthUnit.java
 *
 * Standalone enum responsible for unit conversion.
 * Base unit used internally = inches.
 */

public enum LengthUnit {

    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // convert value in this unit to base unit (inches)
    public double convertToBaseUnit(double value) {

        double baseValue = value * conversionFactor;

        return Math.round(baseValue * 100.0) / 100.0;
    }

    // convert value from base unit (inches) to this unit
    public double convertFromBaseUnit(double baseValue) {

        double result = baseValue / conversionFactor;

        return Math.round(result * 100.0) / 100.0;
    }
}