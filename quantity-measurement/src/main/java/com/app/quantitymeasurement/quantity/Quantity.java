package com.app.quantitymeasurement.quantity;

import com.app.quantitymeasurement.unit.IMeasurable;

public class Quantity {

    private final double value;
    private final IMeasurable unit;

    public Quantity(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    public Quantity convertTo(IMeasurable target) {

        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);

        return new Quantity(converted, target);
    }

    public Quantity add(Quantity other, IMeasurable target) {

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;
        double result = target.convertFromBaseUnit(sum);

        return new Quantity(result, target);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
			return true;
		}
        if (!(obj instanceof Quantity)) {
			return false;
		}

        Quantity other = (Quantity) obj;

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.0001;
    }
}