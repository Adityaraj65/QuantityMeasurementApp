package com.app.quantitymeasurement.repository;

import java.util.List;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();

    List<QuantityMeasurementEntity> getByOperation(String operation);

    List<QuantityMeasurementEntity> getByMeasurementType(String type);

    int getTotalCount();

    void deleteAll();

    String getPoolStatistics();

    void releaseResources();
}