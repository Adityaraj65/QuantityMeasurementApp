package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}

        if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Invalid value");
		}

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null) {
			throw new IllegalArgumentException("Quantity cannot be null");
		}

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        // check same reference
        if (this == obj) {
			return true;
		}

        // check null or different class
        if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

        Quantity<?> other = (Quantity<?>) obj;

        // prevent comparing different measurement categories
        if (!unit.getClass().equals(other.unit.getClass())) {
			return false;
		}

        // convert both quantities to base unit
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        // allow small floating precision difference
        return Math.abs(base1 - base2) < 0.001;
    }

    @Override
    public int hashCode() {

        double base = unit.convertToBaseUnit(value);
        return Objects.hash(base);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}