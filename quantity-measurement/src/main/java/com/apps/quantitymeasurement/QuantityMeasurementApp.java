package com.apps.quantitymeasurement;

// class used to demonstrate operations
public class QuantityMeasurementApp {

    // check equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    // compare two values with units
    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return demonstrateLengthEquality(length1, length2);
    }

    // convert using raw value
    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);

        return length.convertTo(toUnit);
    }

    // convert using object
    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    // UC6 addition
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        return length1.add(length2);
    }
 // demonstrate addition with explicit target unit
    public static Length demonstrateLengthAddition(Length length1, Length length2, Length.LengthUnit targetUnit) {

        return length1.add(length2, targetUnit);
    }
 
    public static void main(String[] args) {

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = demonstrateLengthAddition(length1, length2);

        System.out.println(result);
    }
}