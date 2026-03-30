package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesEquality() {

        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testFeetInchesComparison() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testFeetInequality() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testInchesInequality() {

        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(24.0, LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testCrossUnitInequality() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(13.0, LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void testMultipleFeetComparison() {

        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void yardEquals36Inches() {

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {

        Length l1 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(39.37, LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void threeFeetEqualsOneYard() {

        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {

        Length l1 = new Length(30.48, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void yardNotEqualToInches() {

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(35.0, LengthUnit.INCHES);

        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    public void referenceEqualitySameObject() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l1));
    }

    @Test
    public void equalsReturnsFalseForNull() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    @Test
    public void convertFeetToInches() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                3.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        Length expected = new Length(36.0, LengthUnit.INCHES);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(result, expected)
        );
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {

        Length length = new Length(2.0, LengthUnit.YARDS);

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                length,
                LengthUnit.INCHES
        );

        Length expected = new Length(72.0, LengthUnit.INCHES);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(result, expected)
        );
    }

    @Test
    public void addFeetAndInches() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(
                length1,
                length2
        );

        Length expectedLength = new Length(2.0, LengthUnit.FEET);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength)
        );
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(
                length1,
                length2,
                LengthUnit.INCHES
        );

        Length expectedLength = new Length(24.0, LengthUnit.INCHES);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength)
        );
    }
    @Test
    public void kilogramEqualsGram(){

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }

    @Test
    public void kilogramNotEqualDifferent(){

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

        assertFalse(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }

    @Test
    public void poundEqualsGram(){

        Weight w1 = new Weight(1.0, WeightUnit.POUND);
        Weight w2 = new Weight(453.592, WeightUnit.GRAM);

        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1, w2));
    }

    @Test
    public void convertKilogramToGram(){

        Weight result = QuantityMeasurementApp.demonstrateWeightConversion(
                1.0,
                WeightUnit.KILOGRAM,
                WeightUnit.GRAM
        );

        Weight expected = new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(result.equals(expected));
    }

    @Test
    public void convertPoundToKilogram(){

        Weight result = QuantityMeasurementApp.demonstrateWeightConversion(
                2.20462,
                WeightUnit.POUND,
                WeightUnit.KILOGRAM
        );

        Weight expected = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(result.equals(expected));
    }

    @Test
    public void addKilogramAndGram(){

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = QuantityMeasurementApp.demonstrateWeightAddition(w1, w2);

        Weight expected = new Weight(2.0, WeightUnit.KILOGRAM);

        assertTrue(result.equals(expected));
    }

    @Test
    public void addWithTargetUnitGram(){

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = QuantityMeasurementApp.demonstrateWeightAddition(
                w1,
                w2,
                WeightUnit.GRAM
        );

        Weight expected = new Weight(2000.0, WeightUnit.GRAM);

        assertTrue(result.equals(expected));
    }
}