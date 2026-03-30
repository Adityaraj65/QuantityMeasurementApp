package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.IMeasurable;
import com.apps.quantitymeasurement.LengthUnit;
import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.TemperatureUnit;
import com.apps.quantitymeasurement.VolumeUnit;
import com.apps.quantitymeasurement.WeightUnit;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

@SuppressWarnings({"rawtypes","unchecked"})
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    /**
     * Create Quantity from DTO
     */
    private Quantity createQuantity(QuantityDTO dto) {

        if (dto == null) {
            throw new QuantityMeasurementException("QuantityDTO cannot be null");
        }

        Enum unitEnum = (Enum) resolveUnit(dto.getMeasurementType(), dto.getUnit());

        return new Quantity(dto.getValue(), unitEnum);
    }

    /**
     * Resolve unit enum
     */
    private IMeasurable resolveUnit(String type, String unitName) {

        if (type == null || unitName == null) {
            throw new QuantityMeasurementException("Measurement type or unit cannot be null");
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
                throw new QuantityMeasurementException("Unsupported measurement type: " + type);
        }
    }

    /**
     * Compare quantities
     */
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity quantity1 = createQuantity(q1);
        Quantity quantity2 = createQuantity(q2);

        return quantity1.equals(quantity2);
    }

    /**
     * Convert quantity
     */
    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        Quantity q = createQuantity(quantity);

        Enum target = (Enum) resolveUnit(quantity.getMeasurementType(), targetUnit);

        Quantity result = q.convertTo(target);

        return new QuantityDTO(
                result.getValue(),
                targetUnit,
                quantity.getMeasurementType()
        );
    }

    /**
     * Add quantities
     */
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String targetUnit) {

        Quantity quantity1 = createQuantity(q1);
        Quantity quantity2 = createQuantity(q2);

        Enum target = (Enum) resolveUnit(q1.getMeasurementType(), targetUnit);

        Quantity result = quantity1.add(quantity2, target);

        return new QuantityDTO(
                result.getValue(),
                targetUnit,
                q1.getMeasurementType()
        );
    }

    /**
     * Subtract quantities (manual implementation since Quantity.java doesn't have subtract)
     */
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String targetUnit) {

        Quantity quantity1 = createQuantity(q1);
        Quantity quantity2 = createQuantity(q2);

        Enum target = (Enum) resolveUnit(q1.getMeasurementType(), targetUnit);

        double base1 = ((IMeasurable) quantity1.getUnit()).convertToBaseUnit(quantity1.getValue());
        double base2 = ((IMeasurable) quantity2.getUnit()).convertToBaseUnit(quantity2.getValue());

        double diff = base1 - base2;

        double resultValue = ((IMeasurable) target).convertFromBaseUnit(diff);

        Quantity result = new Quantity(resultValue, target);

        return new QuantityDTO(
                result.getValue(),
                targetUnit,
                q1.getMeasurementType()
        );
    }

    /**
     * Divide quantities (manual implementation)
     */
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity quantity1 = createQuantity(q1);
        Quantity quantity2 = createQuantity(q2);

        double base1 = ((IMeasurable) quantity1.getUnit()).convertToBaseUnit(quantity1.getValue());
        double base2 = ((IMeasurable) quantity2.getUnit()).convertToBaseUnit(quantity2.getValue());

        if (base2 == 0) {
            throw new QuantityMeasurementException("Division by zero");
        }

        return base1 / base2;
    }
}