package com.apps.quantitymeasurement;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {
	  // Converts the given value to the category's base unit (e.g., inches → feet)
    double convertToBaseUnit(double value);

    // Converts a base unit value to the current unit (e.g., feet → inches)
    double convertFromBaseUnit(double baseValue);
    // Returns the name of the unit (used for display or identification)
    String getUnitName();
    // Lambda implementation indicating arithmetic operations are supported by default

    SupportsArithmetic supportsArithmetic = () -> true;
    // Default method that checks whether arithmetic operations are allowed
    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // default: all operations allowed
    }
}