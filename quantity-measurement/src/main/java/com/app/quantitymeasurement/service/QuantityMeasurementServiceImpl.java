package com.app.quantitymeasurement.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            throw new UnsupportedOperationException(q1.getMeasurementType() + " does not support arithmetic operations.");
        }
    }

    private Quantity toQuantity(QuantityDTO dto) {
        return new Quantity(dto.getValue(), getUnit(dto));
    }

    private void save(QuantityMeasurementDTO dto) {
        QuantityMeasurementEntity e = new QuantityMeasurementEntity();
        
        // Mapping existing fields
        e.setThisValue(dto.getThisValue());
        e.setThatValue(dto.getThatValue());
        e.setMeasurementType(dto.getMeasurementType());
        e.setOperation(dto.getOperation());
        e.setResultValue(dto.getResultValue());
        e.setError(dto.isError());
        e.setErrorMessage(dto.getErrorMessage());

        // Security Logic using Authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            e.setUserEmail(auth.getName()); 
        }

        repository.save(e);
    }

    private QuantityMeasurementDTO buildResponse(QuantityDTO q1, QuantityDTO q2, double result, String op) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2 != null ? q2.getValue() : 0);
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation(op);
        dto.setResultValue(result);
        dto.setError(false);
        save(dto);
        return dto;
    }
    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).add(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "ADD");
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).subtract(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "SUBTRACT");
    }

    @Override
    public QuantityMeasurementDTO multiply(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).multiply(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "MULTIPLY");
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2) {
        validateArithmeticSupport(q1);
        double result = toQuantity(q1).divide(toQuantity(q2), getUnit(q1)).getValue();
        return buildResponse(q1, q2, result, "DIVIDE");
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {
        boolean isEqual = toQuantity(q1).equals(toQuantity(q2));
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(q1.getValue());
        dto.setThatValue(q2.getValue());
        dto.setMeasurementType(q1.getMeasurementType());
        dto.setOperation("COMPARE");
        dto.setResultValue(isEqual ? 1.0 : 0.0); // 1.0 for true, 0.0 for false
        dto.setResultString(String.valueOf(isEqual));
        save(dto);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO q, String targetUnit) {
        QuantityDTO targetDto = new QuantityDTO();
        targetDto.setMeasurementType(q.getMeasurementType());
        targetDto.setUnit(targetUnit);
        
        Quantity result = toQuantity(q).convertTo(getUnit(targetDto));
        return buildResponse(q, null, result.getValue(), "CONVERT");
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return repository.findByOperation(operation).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = auth.getName();
                              
        
        return repository.findByMeasurementTypeAndUserEmail(type, currentEmail)
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
        return repository.findByErrorTrue().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private QuantityMeasurementDTO mapToDTO(QuantityMeasurementEntity e) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(e.getThisValue());
        dto.setThatValue(e.getThatValue());
        dto.setMeasurementType(e.getMeasurementType());
        dto.setOperation(e.getOperation());
        dto.setResultValue(e.getResultValue());
        dto.setError(e.isError());
        return dto;
    }
}