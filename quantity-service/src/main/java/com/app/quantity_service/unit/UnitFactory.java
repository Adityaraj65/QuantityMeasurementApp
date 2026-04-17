package com.app.quantity_service.unit;

public class UnitFactory {
    public static IMeasurable getUnit(String unitName, String type) {
        return switch (type.toUpperCase()) {
            case "LENGTH" -> LengthUnit.valueOf(unitName.toUpperCase());
            case "VOLUME" -> VolumeUnit.valueOf(unitName.toUpperCase());
            case "WEIGHT" -> WeightUnit.valueOf(unitName.toUpperCase());
            case "TEMPERATURE" -> TemperatureUnit.valueOf(unitName.toUpperCase());
            default -> throw new IllegalArgumentException("Invalid Type: " + type);
        };
    }
}