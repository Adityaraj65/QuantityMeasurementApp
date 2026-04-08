package com.app.quantitymeasurement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // Helper: Map String types/units to the correct Enum
    private IMeasurable resolveUnit(QuantityDTO dto) {
        String type = dto.getMeasurementType();
        String unitName = dto.getUnit().toUpperCase();

        return switch (type) {
            case "LengthUnit" -> LengthUnit.valueOf(unitName);
            case "VolumeUnit" -> VolumeUnit.valueOf(unitName);
            case "WeightUnit" -> WeightUnit.valueOf(unitName);
            case "TemperatureUnit" -> TemperatureUnit.valueOf(unitName);
            default -> throw new IllegalArgumentException("Unknown Type: " + type);
        };
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            double v1 = resolveUnit(q1).convertToBaseUnit(q1.getValue());
            double v2 = resolveUnit(q2).convertToBaseUnit(q2.getValue());
            boolean result = Math.abs(v1 - v2) < 0.0001;

            dto.setOperation("COMPARE");
            dto.setResultString(String.valueOf(result));
            updateDtoValues(dto, q1, q2);
        } catch (Exception e) {
            handleError(dto, e);
        }
        save(dto, q1, q2);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2) {
        return performArithmetic(q1, q2, "ADD");
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return performArithmetic(q1, q2, "SUBTRACT");
    }

    @Override
    public QuantityMeasurementDTO multiply(QuantityDTO q1, QuantityDTO q2) {
        return performArithmetic(q1, q2, "MULTIPLY");
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2) {
        return performArithmetic(q1, q2, "DIVIDE");
    }

    private QuantityMeasurementDTO performArithmetic(QuantityDTO q1, QuantityDTO q2, String op) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            IMeasurable u1 = resolveUnit(q1);
            IMeasurable u2 = resolveUnit(q2);
            u1.validateOperationSupport(op); // Blocks temperature math

            double v1 = u1.convertToBaseUnit(q1.getValue());
            double v2 = u2.convertToBaseUnit(q2.getValue());

            double res = switch (op) {
                case "ADD" -> v1 + v2;
                case "SUBTRACT" -> v1 - v2;
                case "MULTIPLY" -> v1 * v2;
                case "DIVIDE" -> v1 / v2;
                default -> 0;
            };

            dto.setOperation(op);
            dto.setResultValue(res);
            updateDtoValues(dto, q1, q2);
        } catch (Exception e) {
            handleError(dto, e);
        }
        save(dto, q1, q2);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO q1, QuantityDTO target) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            IMeasurable source = resolveUnit(q1);
            IMeasurable dest = resolveUnit(target);
            double base = source.convertToBaseUnit(q1.getValue());
            dto.setResultValue(dest.convertFromBaseUnit(base));
            dto.setOperation("CONVERT");
            updateDtoValues(dto, q1, target);
        } catch (Exception e) {
            handleError(dto, e);
        }
        save(dto, q1, target);
        return dto;
    }

    // ================= HELPERS & DB =================

    private void updateDtoValues(QuantityMeasurementDTO dto, QuantityDTO q1, QuantityDTO q2) {
        dto.setThisValue(q1.getValue());
        if (q2 != null) {
			dto.setThatValue(q2.getValue());
		}
        dto.setError(false);
    }

    private void handleError(QuantityMeasurementDTO dto, Exception e) {
        dto.setError(true);
        dto.setErrorMessage(e.getMessage());
    }

    private void save(QuantityMeasurementDTO dto, QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(dto.getThisValue());
        entity.setThisUnit(q1.getUnit());
        entity.setOperation(dto.getOperation());
        entity.setResultValue(dto.getResultValue());
        entity.setError(dto.isError());
        entity.setErrorMessage(dto.getErrorMessage());
        repository.save(entity);
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String op) {
        return repository.findByOperation(op).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
        return repository.findByThisMeasurementType(type).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public long getOperationCount(String op) {
        return repository.countByOperationAndIsErrorFalse(op);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return repository.findByIsErrorTrue().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private QuantityMeasurementDTO mapToDto(QuantityMeasurementEntity entity) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(entity.getThisValue());
        dto.setOperation(entity.getOperation());
        dto.setResultValue(entity.getResultValue());
        dto.setError(entity.isError());
        return dto;
    }
}