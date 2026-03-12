package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends Enum<U> & IMeasurable> {

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

    private void validateCategory(Quantity<U> other) {

        if (other == null) {
			throw new IllegalArgumentException("Quantity cannot be null");
		}

        if (!unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Different measurement categories");
		}
    }

    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateCategory(other);

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        result = Math.round(result * 1000.0) / 1000.0;

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
			return true;
		}

        if (!(obj instanceof Quantity<?>)) {
			return false;
		}

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) {
			return false;
		}

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

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