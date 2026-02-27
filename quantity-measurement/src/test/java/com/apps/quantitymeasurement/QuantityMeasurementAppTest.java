package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void yardEqualsYard() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(1.0, Length.LengthUnit.YARDS)));
    }

    @Test
    void yardEqualsFeet() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    void yardEqualsInches() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    void centimeterEqualsInches() {
        assertTrue(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES)));
    }

    @Test
    void centimeterNotEqualFeet() {
        assertFalse(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    void transitiveProperty() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    void sameReference() {
        Length length = new Length(2.0, Length.LengthUnit.FEET);
        assertEquals(length, length);
    }

    @Test
    void nullComparison() {
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        assertNotEquals(length, null);
    }
}