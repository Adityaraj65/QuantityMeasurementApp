package com.apps.quantitymeasurement;

// class representing a length value with its unit
public class Length {

    // value of the length
    private double value;

    // unit of the length
    private LengthUnit unit;

    // enum storing units with conversion factor to inches
    public enum LengthUnit {

        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    // constructor to create length object
    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // convert this length to inches
    private double convertToBaseUnit() {
        return value * unit.getFactor();
    }

    // compare two lengths using inches
    private boolean compare(Length that) {

        double thisInches = this.convertToBaseUnit();
        double thatInches = that.convertToBaseUnit();

        return Math.abs(thisInches - thatInches) < 0.01;
    }

    // override equals method
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

    // convert to another unit
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}

        double inches = convertToBaseUnit();

        double converted = inches / targetUnit.getFactor();

        converted = Math.round(converted * 100.0) / 100.0;

        return new Length(converted, targetUnit);
    }

    // add two lengths
    public Length add(Length that) {

        if (that == null) {
			throw new IllegalArgumentException("Length cannot be null");
		}

        double thisInches = this.convertToBaseUnit();
        double thatInches = that.convertToBaseUnit();

        double sum = thisInches + thatInches;

        double result = sum / this.unit.getFactor();

        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, this.unit);
    }

    // convert inches to target unit
    private double convertFromBaseToTargetUnit(double inches, LengthUnit targetUnit) {

        double value = inches / targetUnit.getFactor();

        return Math.round(value * 100.0) / 100.0;
    }

    // readable output
    @Override
    public String toString() {
        return value + " " + unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }
}