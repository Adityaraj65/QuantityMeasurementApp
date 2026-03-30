package com.apps.quantitymeasurement;

//Enum representing different units of length
//enum mean fixed set of constants
public enum LengthUnit implements IMeasurable {
	// Conversion factors relative to base unit (feet)
	
    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(0.0328084);
	 // Factor used to convert this unit to the base unit
    private final double factor;

    // Constructor initializes conversion factor for each unit
//    Each enum constant calls this constructor.
    //for feet(1.0) factor =1.0 similarly for other
    LengthUnit(double factor) {
        this.factor = factor;
    }
    // Converts a value of this unit into base unit (feet)
    @Override
	public double convertToBaseUnit(double value) {
        return value * factor;
    }

    // Converts base unit value (feet) back into this unit
    @Override
	public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
    // Returns enum constant name as the unit name
    @Override
	public String getUnitName() {
//    	name() is a built-in enum method and it return constant name 
        return name();
    }
}