package feetmeasurementequality;

/**
 * UC1: Feet Measurement Equality
 * This class compares two feet measurements for equality.
 */
public class FeetMeasurement {

    // Encapsulated feet value (immutable)
    private final double value;

    /**
     * Constructor to initialize feet value
     */
    public FeetMeasurement(double value) {
        this.value = value;
    }

    /**
     * Equality check based on value
     */
    @Override
    public boolean equals(Object obj) {

        // Same reference check
        if (this == obj) {
            return true;
        }

        // Null check
        if (obj == null) {
            return false;
        }

        // Type safety check
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        // Safe cast
        FeetMeasurement other = (FeetMeasurement) obj;

        // Floating point comparison
        return Double.compare(this.value, other.value) == 0;
    }

    /**
     * Main method – required by UC1
     * Demonstrates feet measurement equality
     */
    public static void main(String[] args) {

        // User input (simulated)
        FeetMeasurement feet1 = new FeetMeasurement(1.0);
        FeetMeasurement feet2 = new FeetMeasurement(1.0);

        // Equality check
        boolean result = feet1.equals(feet2);

        // Output
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}
