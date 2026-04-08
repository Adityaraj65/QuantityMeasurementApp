package com.app.quantitymeasurement.quantity;

import com.app.quantitymeasurement.unit.IMeasurable;

public class Quantity {

    private final double value;
    private final IMeasurable unit;

    public Quantity(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    /**
     * Checks equality by comparing values in their base units.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if (!(obj instanceof Quantity other)) {
			return false;
		}

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        // Using a small epsilon for double comparison to avoid precision issues
        return Math.abs(base1 - base2) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }
}