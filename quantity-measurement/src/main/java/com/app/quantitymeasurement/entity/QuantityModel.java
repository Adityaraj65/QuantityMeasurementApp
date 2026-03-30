package com.app.quantitymeasurement.entity;

import com.app.quantitymeasurement.unit.IMeasurable;

public class QuantityModel {

    private double value;
    private IMeasurable unit;

    public QuantityModel(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }
}