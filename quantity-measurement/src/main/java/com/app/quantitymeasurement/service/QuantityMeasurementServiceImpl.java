package com.app.quantitymeasurement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

/**
 * Business logic implementation
 */
@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        try {
            boolean result = q1.getValue() == q2.getValue();

            dto.setThisValue(q1.getValue());
            dto.setThatValue(q2.getValue());
            dto.setOperation("COMPARE");
            dto.setResultString(String.valueOf(result));
            dto.setError(false);

        } catch (Exception e) {
            dto.setError(true);
            dto.setErrorMessage(e.getMessage());
        }

        save(dto);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        try {
            double result = q1.getValue() + q2.getValue();

            dto.setThisValue(q1.getValue());
            dto.setThatValue(q2.getValue());
            dto.setOperation("ADD");
            dto.setResultValue(result);
            dto.setError(false);

        } catch (Exception e) {
            dto.setError(true);
            dto.setErrorMessage(e.getMessage());
        }

        save(dto);
        return dto;
    }

    private void save(QuantityMeasurementDTO dto) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(dto.getThisValue());
        entity.setThatValue(dto.getThatValue());
        entity.setOperation(dto.getOperation());
        entity.setResultValue(dto.getResultValue());
        entity.setError(dto.isError());
        entity.setErrorMessage(dto.getErrorMessage());

        repository.save(entity);
    }

    @Override
    public List<QuantityMeasurementDTO> getByOperation(String operation) {
        return new ArrayList<>();
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return new ArrayList<>();
    }
}