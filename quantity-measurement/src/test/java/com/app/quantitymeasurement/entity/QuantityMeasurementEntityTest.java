package com.app.quantitymeasurement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementEntityTest {

    @Test
    void testEntity() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "2 FEET", null, "length");

        assertEquals("ADD", entity.getOperation());
        assertEquals("2 FEET", entity.getResult());
        assertNull(entity.getError());
        assertEquals("length", entity.getMeasurementType());
    }
}