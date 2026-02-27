package com.apps.quantitymeasurement;

public class Length {

    // numeric value of measurement
    private final double value;

    // unit of measurement
    private final LengthUnit unit;

    // supported units with conversion factor to inches (base unit)
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        double toInches(double value) {
            return value * toInchesFactor;
        }
    }

    // create immutable Length object
    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // convert current value to inches
    private double toBaseUnit() {
        return unit.toInches(value);
    }

    // equality based on converted inch value
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }
}