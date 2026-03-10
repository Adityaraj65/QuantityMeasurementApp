package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // constructor
    public Quantity(double value, U unit) {

        if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}

        if (Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Invalid numeric value");
		}

        this.value = value;
        this.unit = unit;
    }

    // getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // conversion
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // addition (implicit unit)
    public Quantity<U> add(Quantity<U> other) {

        return add(other, this.unit);
    }

    // addition (target unit)
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null) {
			throw new IllegalArgumentException("Quantity cannot be null");
		}

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        if (!unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Incompatible unit types");
		}

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // subtraction (implicit unit)
    public Quantity<U> subtract(Quantity<U> other) {

        return subtract(other, this.unit);
    }

    // subtraction (target unit)
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        if (!unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Incompatible unit types");
		}

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double baseResult = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // division
    public double divide(Quantity<U> other) {

        if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}

        if (!unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Incompatible unit types");
		}

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0) {
			throw new ArithmeticException("Division by zero");
		}

        return base1 / base2;
    }

    // equals
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
			return true;
		}

        if (obj == null || getClass() != obj.getClass()) {
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

    // hashCode
    @Override
    public int hashCode() {

        double base = unit.convertToBaseUnit(value);
        return Objects.hash(base);
    }

    // string representation
    @Override
    public String toString() {

        return "Quantity(" + value + ", " + unit + ")";
    }
}