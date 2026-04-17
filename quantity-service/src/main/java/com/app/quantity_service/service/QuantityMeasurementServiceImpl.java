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
        // Aligned with your specific measurement type strings
        switch (dto.getMeasurementType()) {
            case "LengthUnit": return LengthUnit.valueOf(dto.getUnit());
            case "WeightUnit": return WeightUnit.valueOf(dto.getUnit());
            case "VolumeUnit": return VolumeUnit.valueOf(dto.getUnit());
            case "TemperatureUnit": return TemperatureUnit.valueOf(dto.getUnit());
            default: throw new RuntimeException("Invalid measurement type: " + dto.getMeasurementType());
        }
    }

    private void validateArithmeticSupport(QuantityDTO q1) {
        if (!getUnit(q1).supportsArithmetic()) {
            throw new UnsupportedOperationException(q1.getMeasurementType() + " does not support arithmetic operations.");
        }
    }

    private Quantity toQuantity(QuantityDTO dto) {
        return new Quantity(dto.getValue(), getUnit(dto));
    }

    /**
     * Internal method to map DTO data to Entity.
     * Note: We pass the email string directly instead of using SecurityContextHolder.
     */
    private void save(QuantityMeasurementDTO dto, String email) {
        QuantityMeasurement e = new QuantityMeasurement();
        
        // Mapping fields to match your Entity field names
        e.setValOne(dto.getThisValue());
        e.setValTwo(dto.getThatValue());
        e.setMeasurementType(dto.getMeasurementType());
        e.setOperation(dto.getOperation());
        e.setCalculationResult(dto.getResultValue());
        e.setStatus(dto.isError() ? "ERROR" : "SUCCESS");
        e.setUserEmail(email); 

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
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).add(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "ADD", email);
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String email) {
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).subtract(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "SUBTRACT", email);
    }

    @Override
    public QuantityMeasurementDTO multiply(QuantityDTO q1, QuantityDTO q2, String email) {
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).multiply(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "MULTIPLY", email);
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2, String email) {
        validateArithmeticSupport(q1);
        if (q2.getValue() == 0) {
			throw new ArithmeticException("Cannot divide by zero");
		}
        double result = toQuantity(q1).divide(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "DIVIDE", email);
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2, String email) {
        boolean isEqual = toQuantity(q1).equals(toQuantity(q2));
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2.getValue());
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation("COMPARE");
        dto.setResultValue(isEqual ? 1.0 : 0.0);
        dto.setError(false);
        
        save(dto, email);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit, String email) {
        QuantityDTO targetDto = new QuantityDTO();
        targetDto.setMeasurementType(q.getMeasurementType());
        targetDto.setUnit(targetUnit);
        
        Quantity result = toQuantity(q).convertTo(getUnit(targetDto));
        return buildResponse(q, null, result.getValue(), "CONVERT", email);
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation, String email) {
        return repository.findByUserEmailAndOperation(email, operation)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type, String email) {
        return repository.findByUserEmailAndMeasurementType(email, type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long getOperationCount(String operation, String email) {
        return repository.countByUserEmailAndOperation(email, operation);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory(String email) {
        return repository.findByUserEmailAndStatus(email, "ERROR")
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

 // Inside QuantityMeasurementServiceImpl.java

    private QuantityMeasurementDTO mapToDTO(QuantityMeasurement e) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        // Database Column -> DTO Field
        dto.setThisValue(e.getValOne()); 
        dto.setThatValue(e.getValTwo());
        dto.setMeasurementType(e.getMeasurementType());
        dto.setOperation(e.getOperation());
        dto.setResultValue(e.getCalculationResult());
        dto.setError("ERROR".equals(e.getStatus()));
        return dto;
    }
}