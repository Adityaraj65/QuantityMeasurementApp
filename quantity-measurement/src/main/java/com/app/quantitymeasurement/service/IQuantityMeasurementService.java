package com.app.quantitymeasurement.service;

import java.util.List;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {

    QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementDTO convert(QuantityDTO q1, QuantityDTO target);

    List<QuantityMeasurementDTO> getOperationHistory(String operation);

    List<QuantityMeasurementDTO> getMeasurementsByType(String type);

    long getOperationCount(String operation);

    List<QuantityMeasurementDTO> getErrorHistory();

	QuantityMeasurementDTO multiply(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
}