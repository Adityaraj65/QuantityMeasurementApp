package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository 
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // ================= UC17 METHODS =================

    // Operation history
    List<QuantityMeasurementEntity> findByOperation(String operation);

    // Type history
    List<QuantityMeasurementEntity> findByMeasurementType(String measurementType);

    // Count operations
    long countByOperation(String operation);

    // Error history
    List<QuantityMeasurementEntity> findByErrorTrue();

    List<QuantityMeasurementEntity> findByMeasurementTypeAndUserEmail(String type, String email);
}