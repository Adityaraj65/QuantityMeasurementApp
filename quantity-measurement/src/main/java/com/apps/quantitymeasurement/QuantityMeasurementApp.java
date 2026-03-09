package com.apps.quantitymeasurement;

/**
 * QuantityMeasurementApp
 * Demonstrates equality, conversion and addition operations.
 */

public class QuantityMeasurementApp {

    // equality demonstration
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    // comparison demonstration
    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return demonstrateLengthEquality(length1, length2);
    }

    // conversion using raw values
    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    // conversion using object
    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    // UC6 addition
    public static Length demonstrateLengthAddition(Length length1, Length length2) {

        return length1.add(length2);
    }

    // UC7 addition with explicit target unit
    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {

        return length1.add(length2, targetUnit);
    }

    // ---------------- UC9 Weight Operations ----------------

    // weight equality
    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {

        return w1.equals(w2);
    }

    // weight conversion
    public static Weight demonstrateWeightConversion(double value, WeightUnit from, WeightUnit to) {

        Weight weight = new Weight(value, from);
        return weight.convertTo(to);
    }

    // weight addition (default unit)
    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {

        return w1.add(w2);
    }

    // weight addition with explicit target unit
    public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit target) {

        return w1.add(w2, target);
    }

    public static void main(String[] args) {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length result = demonstrateLengthAddition(length1, length2, LengthUnit.FEET);

        System.out.println(result);
    }
}