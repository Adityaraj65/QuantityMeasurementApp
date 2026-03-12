package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performEquality(QuantityDTO q1, QuantityDTO q2) {

        boolean result = service.compare(q1, q2);

        System.out.println("Equality result = " + result);
    }

    public void performConversion(QuantityDTO q, String targetUnit) {

        QuantityDTO result = service.convert(q, targetUnit);

        System.out.println(
                "Converted = " + result.getValue() + " " + result.getUnit());
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2, String targetUnit) {

        QuantityDTO result = service.add(q1, q2, targetUnit);

        System.out.println(
                "Addition result = " + result.getValue() + " " + result.getUnit());
    }

    public void performSubtraction(QuantityDTO q1, QuantityDTO q2, String targetUnit) {

        QuantityDTO result = service.subtract(q1, q2, targetUnit);

        System.out.println(
                "Subtraction result = " + result.getValue() + " " + result.getUnit());
    }

    public void performDivision(QuantityDTO q1, QuantityDTO q2) {

        double result = service.divide(q1, q2);

        System.out.println("Division result = " + result);
    }
}