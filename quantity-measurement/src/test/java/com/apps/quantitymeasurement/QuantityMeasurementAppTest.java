package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * UC3 Test cases
 */
public class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_SameValue() {
        Length f1 = new Length(5.0, Length.Unit.FEET);
        Length f2 = new Length(5.0, Length.Unit.FEET);
        assertEquals(f1, f2);
    }

    @Test
    void testInchesEquality_SameValue() {
        Length i1 = new Length(10.0, Length.Unit.INCHES);
        Length i2 = new Length(10.0, Length.Unit.INCHES);
        assertEquals(i1, i2);
    }

    @Test
    void testFeetAndInchesEquality() {
        Length feet = new Length(1.0, Length.Unit.FEET);
        Length inches = new Length(12.0, Length.Unit.INCHES);
        assertEquals(feet, inches);
    }

    @Test
    void testFeetInequality() {
        Length f1 = new Length(1.0, Length.Unit.FEET);
        Length f2 = new Length(2.0, Length.Unit.FEET);
        assertNotEquals(f1, f2);
    }

    @Test
    void testNullComparison() {
        Length f1 = new Length(1.0, Length.Unit.FEET);
        assertNotEquals(f1, null);
    }

    @Test
    void testSameReference() {
        Length f1 = new Length(1.0, Length.Unit.FEET);
        assertEquals(f1, f1);
    }
}