package com.app.quantity_service.service;

import java.util.List;

import com.app.quantity_service.dto.QuantityDTO;
import com.app.quantity_service.dto.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {
    QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2, String email);
    QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String email);
    QuantityMeasurementDTO multiply(QuantityDTO q1, QuantityDTO q2, String email);
    QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2, String email);
    QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2, String email);
    QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit, String email);
    
    List<QuantityMeasurementDTO> getOperationHistory(String operation, String email);
    List<QuantityMeasurementDTO> getMeasurementsByType(String type, String email);
    long getOperationCount(String operation, String email);
    List<QuantityMeasurementDTO> getErrorHistory(String email);
}