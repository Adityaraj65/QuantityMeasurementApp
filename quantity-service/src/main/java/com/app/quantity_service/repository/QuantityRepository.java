package com.app.quantity_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantity_service.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
    
    List<QuantityMeasurementEntity> findByUserEmailAndOperation(String email, String operation);
    
    List<QuantityMeasurementEntity> findByUserEmailAndMeasurementType(String email, String measurementType);
    
    long countByUserEmailAndOperation(String email, String operation);
    
    List<QuantityMeasurementEntity> findByUserEmailAndError(String email, boolean error);
}