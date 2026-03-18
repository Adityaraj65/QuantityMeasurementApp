package com.app.quantitymeasurement.repository;

import java.util.ArrayList;
import java.util.List;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;
    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {}

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return cache;
    }

    @Override
    public List<QuantityMeasurementEntity> getByOperation(String operation) {
        return cache;
    }

    @Override
    public List<QuantityMeasurementEntity> getByMeasurementType(String type) {
        return cache;
    }

    @Override
    public int getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }

    @Override
    public String getPoolStatistics() {
        return "Cache repo (no pool)";
    }

    @Override
    public void releaseResources() {}
}