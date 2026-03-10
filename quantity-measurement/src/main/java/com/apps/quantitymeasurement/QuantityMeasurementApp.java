package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateEquality(Quantity<?> q1, Quantity<?> q2) {
        return q1.equals(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println(demonstrateEquality(t1, t2));

        System.out.println(t1.convertTo(TemperatureUnit.FAHRENHEIT));
    }
}