package com.app.quantitymeasurement.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementControllerTest {

    @Test
    void testEquality() {

        var service = new QuantityMeasurementServiceImpl(
                QuantityMeasurementCacheRepository.getInstance());

        var controller = new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "length");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "length");

        assertTrue(service.compare(q1, q2));
    }
}