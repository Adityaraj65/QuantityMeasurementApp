package com.app.quantitymeasurement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.OperationType;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.*;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        try {
            Quantity quantity1 = toQuantity(q1);
            Quantity quantity2 = toQuantity(q2);

            boolean result = quantity1.equals(quantity2);

            dto.setThisValue(q1.getValue());
            dto.setThatValue(q2.getValue());
            dto.setOperation(OperationType.COMPARE);
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
            Quantity quantity1 = toQuantity(q1);
            Quantity quantity2 = toQuantity(q2);

            quantity1.getUnit().validateOperationSupport("ADD");
            quantity2.getUnit().validateOperationSupport("ADD");

            Quantity result = quantity1.add(quantity2, quantity1.getUnit());

            dto.setThisValue(q1.getValue());
            dto.setThatValue(q2.getValue());
            dto.setOperation(OperationType.ADD);
            dto.setResultValue(result.getValue());
            dto.setError(false);

        } catch (Exception e) {
            dto.setError(true);
            dto.setErrorMessage(e.getMessage());
        }

        save(dto);
        return dto;
    }

    public QuantityMeasurementDTO convert(QuantityDTO q1, QuantityDTO q2) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        try {
            Quantity quantity = toQuantity(q1);
            IMeasurable target = resolveUnit(q2.getMeasurementType(), q2.getUnit());

            Quantity converted = quantity.convertTo(target);

            dto.setThisValue(q1.getValue());
            dto.setOperation(OperationType.CONVERT);
            dto.setResultValue(converted.getValue());
            dto.setError(false);

        } catch (Exception e) {
            dto.setError(true);
            dto.setErrorMessage(e.getMessage());
        }

        save(dto);
        return dto;
    }

    @Override
    public List<QuantityMeasurementDTO> getByOperation(String operation) {

        OperationType op = OperationType.valueOf(operation.toUpperCase());

        return repository.findByOperation(op)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {

        return repository.findByErrorTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Quantity toQuantity(QuantityDTO dto) {
        IMeasurable unit = resolveUnit(dto.getMeasurementType(), dto.getUnit());
        return new Quantity(dto.getValue(), unit);
    }

    private IMeasurable resolveUnit(String type, String unit) {

        switch (type.toUpperCase()) {
            case "LENGTH":
                return LengthUnit.valueOf(unit.toUpperCase());
            case "WEIGHT":
                return WeightUnit.valueOf(unit.toUpperCase());
            case "VOLUME":
                return VolumeUnit.valueOf(unit.toUpperCase());
            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit.toUpperCase());
            default:
                throw new IllegalArgumentException("Invalid measurement type: " + type);
        }
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

    private QuantityMeasurementDTO toDTO(QuantityMeasurementEntity entity) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(entity.getThisValue());
        dto.setThatValue(entity.getThatValue());
        dto.setOperation(entity.getOperation());
        dto.setResultValue(entity.getResultValue());
        dto.setError(entity.isError());
        dto.setErrorMessage(entity.getErrorMessage());

        return dto;
    }
}