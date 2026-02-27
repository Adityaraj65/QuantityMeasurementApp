package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length oneFoot = new Length(1.0, Length.Unit.FEET);
        Length twelveInches = new Length(12.0, Length.Unit.INCHES);

        System.out.println(oneFoot.equals(twelveInches)); // true
    }
}