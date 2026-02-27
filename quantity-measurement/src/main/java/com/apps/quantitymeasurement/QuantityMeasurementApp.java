package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // All units are defined relative to INCHES (base unit)
    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double toInches(double value) {
            return value * toInchesFactor;
        }

        public double fromInches(double inches) {
            return inches / toInchesFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // Convert current length to inches (base unit)
    private double toBaseInches() {
        return unit.toInches(value);
    }

    // UC3 + UC4 equality logic
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length other = (Length) o;
        return Math.abs(this.toBaseInches() - other.toBaseInches()) < 0.000001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseInches() * 1_000_000));
    }

    // UC5: instance conversion
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double inches = toBaseInches();
        double converted = targetUnit.fromInches(inches);
        return new Length(converted, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit);
    }
}