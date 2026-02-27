package com.apps.quantitymeasurement;

/**
 * UC3
 * Single Length class to represent Feet and Inches
 * Removes duplicate code (DRY principle)
 */
public class Length {

    private final double value;
    private final Unit unit;

    /**
     * Enum defines supported units and conversion to base unit (INCH)
     */
    public enum Unit {
        FEET(12.0),     // 1 foot = 12 inches
        INCHES(1.0);    // base unit

        private final double toInches;

        Unit(double toInches) {
            this.toInches = toInches;
        }

        public double toInches() {
            return toInches;
        }
    }

    // Constructor
    public Length(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Convert value to inches
    private double toBaseInches() {
        return value * unit.toInches();
    }

    /**
     * Compare two Length objects after converting to inches
     */
    @Override
    public boolean equals(Object obj) {

        // Same reference
        if (this == obj) {
			return true;
		}

        // Null check
        if (obj == null) {
			return false;
		}

        // Type check
        if (this.getClass() != obj.getClass()) {
			return false;
		}

        // Safe cast
        Length other = (Length) obj;

        // Compare after conversion
        return Double.compare(
                this.toBaseInches(),
                other.toBaseInches()
        ) == 0;
    }
}