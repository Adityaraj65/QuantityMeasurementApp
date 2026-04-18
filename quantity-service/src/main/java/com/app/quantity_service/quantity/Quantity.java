package com.app.quantity_service.quantity;

import com.app.quantity_service.unit.IMeasurable;

public class Quantity {
    private final double value;
    private final IMeasurable unit;

    public Quantity(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    public Quantity convertTo(IMeasurable target) {
        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new Quantity(converted, target);
    }

    public Quantity add(Quantity other, IMeasurable target) {
        double sum = unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        return new Quantity(target.convertFromBaseUnit(sum), target);
    }

    public Quantity subtract(Quantity other, IMeasurable target) {
        double diff = unit.convertToBaseUnit(this.value) - other.unit.convertToBaseUnit(other.value);
        return new Quantity(target.convertFromBaseUnit(diff), target);
    }

    public Quantity multiply(Quantity other, IMeasurable target) {
        double prod = unit.convertToBaseUnit(this.value) * other.unit.convertToBaseUnit(other.value);
        return new Quantity(target.convertFromBaseUnit(prod), target);
    }

    public Quantity divide(Quantity other, IMeasurable target) {
        double div = unit.convertToBaseUnit(this.value) / other.unit.convertToBaseUnit(other.value);
        return new Quantity(target.convertFromBaseUnit(div), target);
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
        double b1 = unit.convertToBaseUnit(this.value);
        double b2 = other.unit.convertToBaseUnit(other.value);
        return Math.abs(b1 - b2) < 0.0001;
    }
}