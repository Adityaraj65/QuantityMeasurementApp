package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    // 1 foot = 12 inches
    @Test
    public void testFeetEqualsInches() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    // 1 yard = 3 feet
    @Test
    public void testYardEqualsFeet() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    // 1 kilogram = 1000 grams
    @Test
    public void testKilogramEqualsGram() {

        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // pound conversion
    @Test
    public void testPoundEqualsGram() {

        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> q2 = new Quantity<>(453.592, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // convert feet → inches
    @Test
    public void testConvertFeetToInches() {

        Quantity<LengthUnit> q = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(36.0, result.getValue());
    }

    // add feet + inches
    @Test
    public void testAddFeetAndInches() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertTrue(result.equals(new Quantity<>(2.0, LengthUnit.FEET)));
    }

    // add kilograms + grams
    @Test
    public void testAddKilogramsAndGrams() {

        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2);

        assertTrue(result.equals(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
    }

    // convert kilogram → gram
    @Test
    public void testConvertKilogramToGram() {

        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue());
    }

    // cross type comparison
    @Test
    public void testLengthNotEqualWeight() {

        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // volume equality
    @Test
    public void testLitreEqualsMillilitre() {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    // gallon conversion
    @Test
    public void testGallonEqualsLitre() {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }

    // convert litre → ml
    @Test
    public void testConvertLitreToMillilitre() {

        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue());
    }

    // add litre + ml
    @Test
    public void testAddLitreAndMillilitre() {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertTrue(result.equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

}