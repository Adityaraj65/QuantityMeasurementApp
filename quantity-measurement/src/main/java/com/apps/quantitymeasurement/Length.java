package com.apps.quantitymeasurement;

/**
 * QuantityLength class responsible for equality,
 * conversion and arithmetic operations.
 * All unit conversion logic is delegated to LengthUnit.
 */

public class Length {

    private double value;
    private LengthUnit unit;

    // constructor
    public Length(double value, LengthUnit unit) {

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

    public LengthUnit getUnit() {
        return unit;
    }

    // convert this length to another unit
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        double baseValue = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Length(converted, targetUnit);
    }

    // UC6 addition
    public Length add(Length thatLength) {

        if (thatLength == null) {
			throw new IllegalArgumentException("Length cannot be null");
		}

        return addAndConvert(thatLength, this.unit);
    }

    // UC7 addition with target unit
    public Length add(Length length, LengthUnit targetUnit) {

        if (length == null) {
			throw new IllegalArgumentException("Length cannot be null");
		}

        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

        return addAndConvert(length, targetUnit);
    }

    // internal method to perform addition
    private Length addAndConvert(Length length, LengthUnit targetUnit) {

        double thisBase = this.unit.convertToBaseUnit(this.value);

        double thatBase = length.unit.convertToBaseUnit(length.value);

        double sumBase = thisBase + thatBase;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }

    // compare two lengths
    private boolean compare(Length thatLength) {

        double thisBase = this.unit.convertToBaseUnit(this.value);

        double thatBase = thatLength.unit.convertToBaseUnit(thatLength.value);

        return Math.abs(thisBase - thatBase) < 0.01;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
			return true;
		}

        if (o == null || getClass() != o.getClass()) {
			return false;
		}

        Length that = (Length) o;

        return compare(that);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // standalone test
    public static void main(String[] args) {

        Length length1 = new Length(1.0, LengthUnit.FEET);

        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length result = length1.add(length2, LengthUnit.FEET);

        System.out.println(result);
    }
}