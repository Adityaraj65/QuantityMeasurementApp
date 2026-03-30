package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private IMeasurable resolveUnit(String type, String unitName) {

        if (type == null || unitName == null) {
            throw new QuantityMeasurementException("Type or Unit cannot be null");
        }

        switch (type.toLowerCase()) {

            case "length":
                return LengthUnit.valueOf(unitName);

            case "weight":
                return WeightUnit.valueOf(unitName);

            case "volume":
                return VolumeUnit.valueOf(unitName);

            case "temperature":
                return TemperatureUnit.valueOf(unitName);

            default:
                throw new QuantityMeasurementException("Unsupported type: " + type);
        }
    }

    private Quantity toQuantity(QuantityDTO dto) {
        return new Quantity(dto.getValue(),
                resolveUnit(dto.getMeasurementType(), dto.getUnit()));
    }

    // ================= COMPARE =================
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        boolean result = toQuantity(q1).equals(toQuantity(q2));

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                String.valueOf(result),
                null,
                q1.getMeasurementType()
        ));

        return result;
    }

    // ================= CONVERT =================
    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        Quantity q = toQuantity(quantity);

        IMeasurable target =
                resolveUnit(quantity.getMeasurementType(), targetUnit);

        Quantity result = q.convertTo(target);

        QuantityDTO dto = new QuantityDTO(
                result.getValue(),
                targetUnit,
                quantity.getMeasurementType()
        );

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                dto.getValue() + " " + dto.getUnit(),
                null,
                dto.getMeasurementType()
        ));

        return dto;
    }

    // ================= ADD =================
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String targetUnit) {

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        IMeasurable target =
                resolveUnit(q1.getMeasurementType(), targetUnit);

        Quantity result = quantity1.add(quantity2, target);

        QuantityDTO dto = new QuantityDTO(
                result.getValue(),
                targetUnit,
                q1.getMeasurementType()
        );

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                dto.getValue() + " " + dto.getUnit(),
                null,
                dto.getMeasurementType()
        ));

        return dto;
    }

    // ================= SUBTRACT =================
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String targetUnit) {

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        IMeasurable target =
                resolveUnit(q1.getMeasurementType(), targetUnit);

        double base1 = quantity1.getUnit().convertToBaseUnit(quantity1.getValue());
        double base2 = quantity2.getUnit().convertToBaseUnit(quantity2.getValue());

        double diff = base1 - base2;
        double result = target.convertFromBaseUnit(diff);

        QuantityDTO dto = new QuantityDTO(result, targetUnit, q1.getMeasurementType());

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                dto.getValue() + " " + dto.getUnit(),
                null,
                dto.getMeasurementType()
        ));

        return dto;
    }

    // ================= DIVIDE =================
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        double base1 = quantity1.getUnit().convertToBaseUnit(quantity1.getValue());
        double base2 = quantity2.getUnit().convertToBaseUnit(quantity2.getValue());

        if (base2 == 0) {
            throw new QuantityMeasurementException("Division by zero");
        }

        double result = base1 / base2;

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                String.valueOf(result),
                null,
                q1.getMeasurementType()
        ));

        return result;
    }
}