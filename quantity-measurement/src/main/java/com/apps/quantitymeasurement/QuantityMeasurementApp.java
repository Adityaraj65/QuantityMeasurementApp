package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC3 / UC4 equality demo
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // UC3 style comparison using raw values
    public static boolean demonstrateLengthComparison(
            double v1, Length.LengthUnit u1,
            double v2, Length.LengthUnit u2) {

        return new Length(v1, u1).equals(new Length(v2, u2));
    }

    // UC5: static conversion API (raw values)
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit fromUnit,
            Length.LengthUnit toUnit) {

        return new Length(value, fromUnit).convertTo(toUnit);
    }

    // UC5: overloaded conversion API (object based)
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(2.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES));
    }
}