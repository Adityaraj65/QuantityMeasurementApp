package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    // 1 foot equals 12 inches
    @Test
    public void lengthFeetEqualsInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
    }

    // 1 yard equals 3 feet
    @Test
    public void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
    }

    // 1 kilogram equals 1000 grams
    @Test
    public void weightKilogramEqualsGrams() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
    }

    // 1 pound equals about 453.592 grams
    @Test
    public void weightPoundEqualsGrams() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> q2 = new Quantity<>(453.592, WeightUnit.GRAM);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
    }

    // convert 3 feet to inches
    @Test
    public void convertLengthFeetToInches() {
        Quantity<LengthUnit> length = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);

        assertTrue(result.equals(new Quantity<>(36.0, LengthUnit.INCHES)));
    }

    // add 1 foot and 12 inches
    @Test
    public void addLengthFeetAndInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateAddition(q1, q2, LengthUnit.FEET);

        assertTrue(result.equals(new Quantity<>(2.0, LengthUnit.FEET)));
    }

    // add 1 kilogram and 1000 grams
    @Test
    public void addWeightKilogramsAndGrams() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateAddition(q1, q2, WeightUnit.KILOGRAM);

        assertTrue(result.equals(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
    }

    // verify generic quantity works with weight type
    @Test
    public void testGenericTypeSafetyWithWeight() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
    }

    // convert kilogram to gram
    @Test
    public void convertWeightKilogramsToGrams() {
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateConversion(weight, WeightUnit.GRAM);

        assertTrue(result.equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    // add kilogram and pound
    @Test
    public void addWeightKilogramsAndPounds() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateAddition(q1, q2, WeightUnit.KILOGRAM);

        assertTrue(result.equals(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
    }

    // convert yard to inches
    @Test
    public void convertLengthYardsToInches() {
        Quantity<LengthUnit> length = new Quantity<>(2.0, LengthUnit.YARDS);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);

        assertTrue(result.equals(new Quantity<>(72.0, LengthUnit.INCHES)));
    }

    // prevent length vs weight equality
    @Test
    public void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // prevent cross type addition
    @Test
    public void preventCrossTypeAdditionLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // prevent converting length to weight
    @Test
    public void preventCrossTypeConversionLengthToWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // add yard and feet
    @Test
    public void addLengthYardsAndFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                QuantityMeasurementApp.demonstrateAddition(q1, q2, LengthUnit.YARDS);

        assertTrue(result.equals(new Quantity<>(2.0, LengthUnit.YARDS)));
    }

    // add kilogram and gram example
    @Test
    public void addWeightTonnesAndKilograms() {
        Quantity<WeightUnit> q1 = new Quantity<>(1000.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                QuantityMeasurementApp.demonstrateAddition(q1, q2, WeightUnit.KILOGRAM);

        assertTrue(result.equals(new Quantity<>(1001.0, WeightUnit.KILOGRAM)));
    }

    // backward compatibility length
    @Test
    public void backwardCompatibilityLengthFeetEqualsInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    // backward compatibility weight
    @Test
    public void backwardCompatibilityWeightKilogramEqualsGrams() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // backward compatibility conversion
    @Test
    public void backwardCompatibilityConvertLengthFeetToInches() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertTrue(result.equals(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    // backward compatibility weight conversion
    @Test
    public void backwardCompatibilityConvertWeightKilogramsToGrams() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

        assertTrue(result.equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    // add same unit length
    @Test
    public void backwardCompatibilityAddLengthInSameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertTrue(result.equals(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    // add same unit weight
    @Test
    public void backwardCompatibilityAddWeightInSameUnit() {
        Quantity<WeightUnit> q1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = q1.add(q2);

        assertTrue(result.equals(new Quantity<>(5.0, WeightUnit.KILOGRAM)));
    }

    // yard equals feet
    @Test
    public void backwardCompatibilityLengthYardsEqualsFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    // pound equals grams
    @Test
    public void backwardCompatibilityWeightPoundEqualsGrams() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> q2 = new Quantity<>(453.592, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // chained addition example
    @Test
    public void backwardCompatibilityChainedAdditionsLength() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2).add(q3);

        assertTrue(result.equals(new Quantity<>(3.0, LengthUnit.FEET)));
    }
}