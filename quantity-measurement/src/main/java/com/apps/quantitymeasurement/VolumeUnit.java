package com.apps.quantitymeasurement;

// volume units implementing IMeasurable interface
public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // return conversion factor relative to base unit
    @Override
	public double getConversionFactor() {
        return conversionFactor;
    }

    // convert value to base unit (litre)
    @Override
	public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // convert from base unit to this unit
    @Override
	public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    // readable unit name
    @Override
	public String getUnitName() {
        return this.name();
    }
}
