package com.app.quantity_service.quantity;

import com.app.quantity_service.unit.IMeasurable;

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

    // ================= CONVERT =================
    public Quantity convertTo(IMeasurable target) {
        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new Quantity(converted, target);
    }

    // ================= ADD =================
    public Quantity add(Quantity other, IMeasurable target) {

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;
        double result = target.convertFromBaseUnit(sum);

        return new Quantity(result, target);
    }

    // ================= SUBTRACT =================
    public Quantity subtract(Quantity other, IMeasurable target) {

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double diff = base1 - base2;
        double result = target.convertFromBaseUnit(diff);

        return new Quantity(result, target);
    }

    // ================= MULTIPLY =================
    public Quantity multiply(Quantity other, IMeasurable target) {

        // Convert other value into this unit
        double otherInThisUnit = other.unit
                .convertToBaseUnit(other.value);

        double thisInBase = this.unit
                .convertToBaseUnit(this.value);

        // Bring both to same unit (this unit)
        double otherConverted = this.unit
                .convertFromBaseUnit(otherInThisUnit);

        // Multiply
        double resultValue = this.value * otherConverted;

        return new Quantity(resultValue, target);
    }

    // ================= DIVIDE =================
    public Quantity divide(Quantity other, IMeasurable target) {

        if (other.value == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        // Convert other into this unit
        double otherInBase = other.unit
                .convertToBaseUnit(other.value);

        double otherConverted = this.unit
                .convertFromBaseUnit(otherInBase);

        double resultValue = this.value / otherConverted;

        return new Quantity(resultValue, target);
    }
    // ================= EQUAL =================
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