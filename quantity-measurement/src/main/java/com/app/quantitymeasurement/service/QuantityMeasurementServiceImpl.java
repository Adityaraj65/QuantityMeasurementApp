package com.app.quantitymeasurement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.quantity.Quantity;
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

    private IMeasurable getUnit(QuantityDTO dto) {
        switch (dto.getMeasurementType()) {
            case "LengthUnit": return LengthUnit.valueOf(dto.getUnit());
            case "WeightUnit": return WeightUnit.valueOf(dto.getUnit());
            case "VolumeUnit": return VolumeUnit.valueOf(dto.getUnit());
            case "TemperatureUnit": return TemperatureUnit.valueOf(dto.getUnit());
            default: throw new RuntimeException("Invalid measurement type");
        }
    }

    private void validateArithmeticSupport(QuantityDTO q1) {
        if (!getUnit(q1).supportsArithmetic()) {
            throw new UnsupportedOperationException(
                q1.getMeasurementType() + " does not support arithmetic operations."
            );
        }
    }

    private Quantity toQuantity(QuantityDTO dto) {
        return new Quantity(dto.getValue(), getUnit(dto));
    }

    private void save(QuantityMeasurementDTO dto) {
        QuantityMeasurementEntity e = new QuantityMeasurementEntity();
        e.setThisValue(dto.getThisValue());
        e.setThatValue(dto.getThatValue());
        e.setMeasurementType(dto.getMeasurementType());
        e.setOperation(dto.getOperation());
        e.setResultValue(dto.getResultValue());
        e.setResultUnit(dto.getResultUnit()); // ✅ IMPORTANT
        e.setError(dto.isError());
        e.setErrorMessage(dto.getErrorMessage());
        repository.save(e);
    }

    // ✅ UPDATED: Quantity pass kar rahe hain instead of double
    private QuantityMeasurementDTO buildResponse(
            QuantityDTO q1,
            QuantityDTO q2,
            Quantity result,
            String op) {

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2 != null ? q2.getValue() : 0);
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation(op);

        dto.setResultValue(result.getValue()); // ✅ value
        dto.setResultUnit(result.getUnit().getUnitName()); // ✅ unit fix

        dto.setError(false);

        save(dto);
        return dto;
    }

    // ================= ADD =================
    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);

        Quantity result = toQuantity(q1)
                .add(toQuantity(q2), getUnit(q1)); // target = q1 unit

        return buildResponse(q1, q2, result, "ADD");
    }

    // ================= SUBTRACT =================
    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);

        Quantity result = toQuantity(q1)
                .subtract(toQuantity(q2), getUnit(q1));

        return buildResponse(q1, q2, result, "SUBTRACT");
    }

    // ================= MULTIPLY =================
    @Override
    public QuantityMeasurementDTO multiply(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);

        Quantity result = toQuantity(q1)
                .multiply(toQuantity(q2), getUnit(q1));

        return buildResponse(q1, q2, result, "MULTIPLY");
    }

    // ================= DIVIDE =================
    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);

        Quantity result = toQuantity(q1)
                .divide(toQuantity(q2), getUnit(q1));

        return buildResponse(q1, q2, result, "DIVIDE");
    }

    // ================= COMPARE =================
    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {

        boolean isEqual = toQuantity(q1).equals(toQuantity(q2));

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2.getValue());
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation("COMPARE");
        dto.setResultValue(isEqual ? 1.0 : 0.0);
        dto.setResultString(String.valueOf(isEqual));

        save(dto);
        return dto;
    }

    // ================= CONVERT =================
    @Override
    public QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit) {

        QuantityDTO targetDto = new QuantityDTO();
        targetDto.setMeasurementType(q.getMeasurementType());
        targetDto.setUnit(targetUnit);

        Quantity result = toQuantity(q).convertTo(getUnit(targetDto));

        return buildResponse(q, null, result, "CONVERT");
    }

    // ================= HISTORY =================
    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return repository.findByOperation(operation)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
        return repository.findByMeasurementType(type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperation(operation);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return repository.findByErrorTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private QuantityMeasurementDTO mapToDTO(QuantityMeasurementEntity e) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(e.getThisValue());
        dto.setThatValue(e.getThatValue());
        dto.setMeasurementType(e.getMeasurementType());
        dto.setOperation(e.getOperation());
        dto.setResultValue(e.getResultValue());
        dto.setResultUnit(e.getResultUnit()); // ✅ IMPORTANT
        dto.setError(e.isError());
        return dto;
    }
}