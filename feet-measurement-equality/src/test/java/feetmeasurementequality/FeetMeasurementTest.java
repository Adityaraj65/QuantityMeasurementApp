package feetmeasurementequality;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FeetMeasurementTest {

    @Test
    void testEquality_SameValue() {
        FeetMeasurement f1 = new FeetMeasurement(1.0);
        FeetMeasurement f2 = new FeetMeasurement(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void testEquality_DifferentValue() {
        FeetMeasurement f1 = new FeetMeasurement(1.0);
        FeetMeasurement f2 = new FeetMeasurement(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void testEquality_NullComparison() {
        FeetMeasurement f1 = new FeetMeasurement(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        FeetMeasurement f1 = new FeetMeasurement(1.0);

        assertTrue(f1.equals(f1));
    }
}
