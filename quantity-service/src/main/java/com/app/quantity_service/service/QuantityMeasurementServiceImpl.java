package com.app.quantity_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantity_service.dto.QuantityDTO;
import com.app.quantity_service.dto.QuantityMeasurementDTO;
import com.app.quantity_service.entity.QuantityMeasurement;
import com.app.quantity_service.quantity.Quantity;
import com.app.quantity_service.repository.QuantityRepository;
import com.app.quantity_service.unit.IMeasurable;
import com.app.quantity_service.unit.LengthUnit;
import com.app.quantity_service.unit.TemperatureUnit;
import com.app.quantity_service.unit.VolumeUnit;
import com.app.quantity_service.unit.WeightUnit;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityRepository repository;

    private IMeasurable getUnit(QuantityDTO dto) {
        switch (dto.getMeasurementType()) {
            case "LengthUnit": return LengthUnit.valueOf(dto.getUnit());
            case "WeightUnit": return WeightUnit.valueOf(dto.getUnit());
            case "VolumeUnit": return VolumeUnit.valueOf(dto.getUnit());
            case "TemperatureUnit": return TemperatureUnit.valueOf(dto.getUnit());
            default: throw new RuntimeException("Invalid type");
        }
    }

    private void save(QuantityMeasurementDTO dto, String email) {
        QuantityMeasurement e = new QuantityMeasurement();

        e.setUserEmail(email);
        e.setValOne(dto.getThisValue());
        e.setValTwo(dto.getThatValue());
        e.setMeasurementType(dto.getMeasurementType());
        e.setOperation(dto.getOperation());
        e.setCalculationResult(dto.getResultValue());
        e.setStatus(dto.isError() ? "ERROR" : "SUCCESS");
        repository.save(e);
    }

    private QuantityMeasurementDTO buildResponse(QuantityDTO q1, QuantityDTO q2, double result, String op, String email) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2 != null ? q2.getValue() : 0);
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation(op);
        dto.setResultValue(result);
        dto.setError(false);
        save(dto, email);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2, String email) {
        double result = new Quantity(q1.getValue(), getUnit(q1)).add(new Quantity(q2.getValue(), getUnit(q2)), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "ADD", email);
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String email) {
        double result = new Quantity(q1.getValue(), getUnit(q1)).subtract(new Quantity(q2.getValue(), getUnit(q2)), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "SUBTRACT", email);
    }

    @Override
    public QuantityMeasurementDTO multiply(QuantityDTO q1, QuantityDTO q2, String email) {
        double result = new Quantity(q1.getValue(), getUnit(q1)).multiply(new Quantity(q2.getValue(), getUnit(q2)), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "MULTIPLY", email);
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2, String email) {
        if (q2.getValue() == 0) {
			throw new ArithmeticException("Divide by zero");
		}
        double result = new Quantity(q1.getValue(), getUnit(q1)).divide(new Quantity(q2.getValue(), getUnit(q2)), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "DIVIDE", email);
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2, String email) {
        boolean isEqual = new Quantity(q1.getValue(), getUnit(q1)).equals(new Quantity(q2.getValue(), getUnit(q2)));
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2.getValue());
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation("COMPARE");
        dto.setResultValue(isEqual ? 1.0 : 0.0);
        save(dto, email);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit, String email) {
        QuantityDTO targetDto = new QuantityDTO();
        targetDto.setMeasurementType(q.getMeasurementType());
        targetDto.setUnit(targetUnit);
        double result = new Quantity(q.getValue(), getUnit(q)).convertTo(getUnit(targetDto)).getValue();
        return buildResponse(q, null, result, "CONVERT", email);
    }

    // --- History methods ---
    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String op, String email) {
        return repository.findByUserEmailAndOperation(email, op).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type, String email) {
        return repository.findByUserEmailAndMeasurementType(email, type).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public long getOperationCount(String op, String email) {
        return repository.countByUserEmailAndOperation(email, op);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory(String email) {
        return repository.findByUserEmailAndStatus(email, "ERROR").stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private QuantityMeasurementDTO mapToDTO(QuantityMeasurement e) {
        QuantityMeasurementDTO d = new QuantityMeasurementDTO();
        d.setThisValue(e.getValOne());
        d.setThatValue(e.getValTwo());
        d.setMeasurementType(e.getMeasurementType());
        d.setOperation(e.getOperation());
        d.setResultValue(e.getCalculationResult());
        d.setError("ERROR".equals(e.getStatus()));
        return d;
    }
}