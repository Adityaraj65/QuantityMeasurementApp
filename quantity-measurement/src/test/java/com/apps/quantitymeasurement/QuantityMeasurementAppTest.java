package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    // -------- FEET TESTS (UC1) --------

    @Test
    public void testFeetEquality_SameValue() {
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(5.0);
        assertEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(6.0);
        assertNotEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(5.0);
        assertNotEquals(f1, null);
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        Feet f1 = new Feet(5.0);
        String other = "5.0";
        assertNotEquals(f1, other);
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet f1 = new Feet(5.0);
        assertEquals(f1, f1);
    }

    // -------- INCHES TESTS (UC2) --------

    @Test
    public void testInchesEquality_SameValue() {
        Inches i1 = new Inches(10.0);
        Inches i2 = new Inches(10.0);
        assertEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        Inches i1 = new Inches(10.0);
        Inches i2 = new Inches(12.0);
        assertNotEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_NullComparison() {
        Inches i1 = new Inches(10.0);
        assertNotEquals(i1, null);
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        Inches i1 = new Inches(10.0);
        Feet f1 = new Feet(10.0);
        assertNotEquals(i1, f1);
    }

    @Test
    public void testInchesEquality_SameReference() {
        Inches i1 = new Inches(10.0);
        assertEquals(i1, i1);
    }
}