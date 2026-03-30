package com.app.quantitymeasurement.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public class QuantityMeasurementDatabaseRepositoryTest {

    private IQuantityMeasurementRepository repo;

    @BeforeEach
    void setup() {
        repo = new QuantityMeasurementDatabaseRepository();
        repo.deleteAll();
    }

    @Test
    void testSaveAndFetch() {

        repo.save(new QuantityMeasurementEntity("ADD", "2 FEET", null, "length"));

        List<QuantityMeasurementEntity> list = repo.getAllMeasurements();

        assertFalse(list.isEmpty());
    }

    @Test
    void testDeleteAll() {

        repo.deleteAll();
        assertEquals(0, repo.getTotalCount());
    }
}