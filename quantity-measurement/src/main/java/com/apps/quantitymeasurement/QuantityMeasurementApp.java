package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // equality demonstration
    public static boolean demonstrateEquality(Quantity<?> q1, Quantity<?> q2) {
        return q1.equals(q2);
    }

    // conversion demonstration
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    // addition with explicit target unit
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.add(q2, targetUnit);
    }

    // subtraction (result in first quantity unit)
    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.subtract(q2);
    }

    // subtraction with explicit target unit
    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.subtract(q2, targetUnit);
    }

    // division demonstration
    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.divide(q2);
    }

    public static void main(String[] args) {

        // Volume examples
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("Equality: " + demonstrateEquality(v1, v2));

        Quantity<VolumeUnit> addResult = demonstrateAddition(v1, v2, VolumeUnit.LITRE);
        System.out.println("Addition: " + addResult);

        Quantity<VolumeUnit> convertResult = demonstrateConversion(v1, VolumeUnit.MILLILITRE);
        System.out.println("Conversion: " + convertResult);

        Quantity<VolumeUnit> subtractResult = demonstrateSubtraction(v1, v2);
        System.out.println("Subtraction: " + subtractResult);

        double divisionResult = demonstrateDivision(v1, v2);
        System.out.println("Division: " + divisionResult);
    }
}