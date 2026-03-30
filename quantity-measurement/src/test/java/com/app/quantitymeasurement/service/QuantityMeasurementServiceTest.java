package com.app.quantitymeasurement.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;

public class QuantityMeasurementServiceTest {

    private final IQuantityMeasurementService service =
            new QuantityMeasurementServiceImpl(
                    QuantityMeasurementCacheRepository.getInstance());

    @Test
    void testCompare() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "length");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "length");
        assertTrue(service.compare(q1, q2));
    }

    @Test
    void testAdd() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "length");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "length");

        QuantityDTO result = service.add(q1, q2, "FEET");

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testConvert() {
        QuantityDTO q = new QuantityDTO(1, "FEET", "length");
        QuantityDTO result = service.convert(q, "INCHES");

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testDivide() {
        QuantityDTO q1 = new QuantityDTO(10, "FEET", "length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "length");

        assertEquals(2.0, service.divide(q1, q2), 0.01);
    }
}