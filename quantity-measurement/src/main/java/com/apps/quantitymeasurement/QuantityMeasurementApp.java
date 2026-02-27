package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // 1. Reference check
            if (this == obj) {
                return true;
            }

            // 2. Null check
            if (obj == null) {
                return false;
            }

            // 3. Type check
            if (this.getClass() != obj.getClass()) {
                return false;
            }

            // 4. Value comparison
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method to demonstrate Feet equality check
    public static void main(String[] args) {
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(5.0);

        System.out.println(f1.equals(f2)); // true
    }
}