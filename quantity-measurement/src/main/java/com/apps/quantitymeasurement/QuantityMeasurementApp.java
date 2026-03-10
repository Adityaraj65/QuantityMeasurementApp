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

    	Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    	Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    	System.out.println(v1.equals(v2));

    	Quantity<VolumeUnit> result = v1.add(v2);
    	System.out.println(result);

    	System.out.println(v1.convertTo(VolumeUnit.MILLILITRE));
    }
}