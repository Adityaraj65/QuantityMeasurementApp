package com.app.quantity_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantity_service.entity.QuantityMeasurement;

@Repository
public interface QuantityRepository extends JpaRepository<QuantityMeasurement, Long> {
    List<QuantityMeasurement> findByUserEmailAndOperation(String email, String operation);
    List<QuantityMeasurement> findByUserEmailAndMeasurementType(String email, String type);
    List<QuantityMeasurement> findByUserEmailAndStatus(String email, String status);
    long countByUserEmailAndOperation(String email, String operation);
}