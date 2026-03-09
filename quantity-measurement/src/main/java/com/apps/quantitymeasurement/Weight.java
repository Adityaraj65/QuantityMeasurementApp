package com.apps.quantitymeasurement;

import java.util.Objects;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    // constructor validates inputs
    public Weight(double value, WeightUnit unit) {

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

    public WeightUnit getUnit() {
        return unit;
    }

    // convert weight to another unit
    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(converted, targetUnit);
    }

    // add weights (result in first unit)
    public Weight add(Weight other) {

        return add(other, this.unit);
    }

    // add weights with target unit
    public Weight add(Weight other, WeightUnit targetUnit) {

        if (other == null) {
			throw new IllegalArgumentException("Weight cannot be null");
		}

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
			return true;
		}

        if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

        Weight other = (Weight) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        // tolerance comparison for floating precision
        return Math.abs(base1 - base2) < 1e-3;
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