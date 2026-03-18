package com.app.quantitymeasurement.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;
    private IQuantityMeasurementRepository repo;

    @BeforeEach
    void setup() {

        repo = new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        controller = new QuantityMeasurementController(service);

        repo.deleteAll();
    }

    @Test
    void testEndToEnd() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "length");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "length");

        controller.performAddition(q1, q2, "FEET");

        assertTrue(repo.getAllMeasurements().size() > 0);
    }
}