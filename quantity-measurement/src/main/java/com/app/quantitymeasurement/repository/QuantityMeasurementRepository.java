package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByThisMeasurementType(String type);

    List<QuantityMeasurementEntity> findByIsErrorTrue();

    long countByOperationAndIsErrorFalse(String operation);
}