package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void feetEqualsFeet() {
        assertTrue(new Length(1, Length.LengthUnit.FEET)
                .equals(new Length(1, Length.LengthUnit.FEET)));
    }

    @Test
    void feetEqualsInches() {
        assertTrue(new Length(1, Length.LengthUnit.FEET)
                .equals(new Length(12, Length.LengthUnit.INCHES)));
    }

    @Test
    void yardEqualsFeet() {
        assertTrue(new Length(1, Length.LengthUnit.YARDS)
                .equals(new Length(3, Length.LengthUnit.FEET)));
    }

    @Test
    void yardEqualsInches() {
        assertTrue(new Length(1, Length.LengthUnit.YARDS)
                .equals(new Length(36, Length.LengthUnit.INCHES)));
    }

    @Test
    void centimeterEqualsInches() {
        assertTrue(new Length(1, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES)));
    }

    @Test
    void convertFeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(3, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertTrue(result.equals(new Length(36, Length.LengthUnit.INCHES)));
    }

    @Test
    void convertYardsToInchesUsingOverload() {
        Length yards = new Length(2, Length.LengthUnit.YARDS);
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(yards, Length.LengthUnit.INCHES);
        assertTrue(result.equals(new Length(72, Length.LengthUnit.INCHES)));
    }

    @Test
    void sameUnitConversionReturnsSameValue() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(5, Length.LengthUnit.FEET, Length.LengthUnit.FEET);
        assertTrue(result.equals(new Length(5, Length.LengthUnit.FEET)));
    }

    @Test
    void zeroValueConversion() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(0.0, result.convertTo(Length.LengthUnit.INCHES).convertTo(Length.LengthUnit.INCHES).equals(new Length(0, Length.LengthUnit.INCHES)) ? 0.0 : 1.0);
    }

    @Test
    void nullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1, null));
    }

    @Test
    void nullTargetUnitThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1, Length.LengthUnit.FEET).convertTo(null));
    }
}