package com.app.quantitymeasurement.service;

import java.util.List;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

/**
 * Service interface
 */
public interface IQuantityMeasurementService {

    QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2);

    List<QuantityMeasurementDTO> getByOperation(String operation);

    List<QuantityMeasurementDTO> getErrorHistory();
}