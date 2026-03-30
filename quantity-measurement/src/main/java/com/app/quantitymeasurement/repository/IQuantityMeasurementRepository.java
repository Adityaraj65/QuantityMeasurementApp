package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

@Repository
public interface IQuantityMeasurementRepository 
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Custom query methods (Spring auto-implements)

    List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByMeasurementType(String measurementType);
}