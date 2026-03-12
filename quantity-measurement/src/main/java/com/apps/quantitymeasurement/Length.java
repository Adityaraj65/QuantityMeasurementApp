package com.apps.quantitymeasurement;

/**
 * QuantityLength class responsible for equality,
 * conversion and arithmetic operations.
 * All unit conversion logic is delegated to LengthUnit.
 */

public class Length {

    // Stores the numeric value of the length
    private double value;

    // Stores the unit associated with the length
    private LengthUnit unit;

    // constructor
    public Length(double value, LengthUnit unit) {

        if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
        // Prevent invalid numeric values like NaN or Infinity
        if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Invalid value");
		}

        this.value = value;
        this.unit = unit;
    }

    // Returns stored length value
    public double getValue() {
        return value;
    }

    // Returns the unit of the length
    public LengthUnit getUnit() {
        return unit;
    }

    // convert this length to another unit
    public Length convertTo(LengthUnit targetUnit) {

        // Validate target unit
        if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
     // Convert current value into base unit
        double baseValue = unit.convertToBaseUnit(value);
        // Convert base unit into target unit
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        // Return new Length object with converted value
        return new Length(converted, targetUnit);
    }

    // UC6 addition
    // Adds another length and returns result in current unit
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

        // Convert both lengths into base unit
        double thisBase = this.unit.convertToBaseUnit(this.value);

        double thatBase = length.unit.convertToBaseUnit(length.value);
        // Add base unit values
        double sumBase = thisBase + thatBase;

        // Convert result to target unit
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }

    // compare two lengths
    private boolean compare(Length thatLength) {

        double thisBase = this.unit.convertToBaseUnit(this.value);

        double thatBase = thatLength.unit.convertToBaseUnit(thatLength.value);

        // Uses tolerance to handle floating point precision errors
        return Math.abs(thisBase - thatBase) < 0.01;
    }

    // Overrides equals to compare lengths based on actual measurement

    @Override
    public boolean equals(Object o) {
    	// Check same object reference
        if (this == o) {
			return true;
		}
        // Ensure object is non-null and same class
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
        // Cast object to Length
        Length that = (Length) o;
        // Compare lengths using base unit conversion
        return compare(that);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // Standalone test to demonstrate addition and conversion
    public static void main(String[] args) {
    	// Create 1 foot
        Length length1 = new Length(1.0, LengthUnit.FEET);

        // Create 12 inches
        Length length2 = new Length(12.0, LengthUnit.INCHES);
     // Add them and convert result to feet
        Length result = length1.add(length2, LengthUnit.FEET);

        System.out.println(result);
    }
}