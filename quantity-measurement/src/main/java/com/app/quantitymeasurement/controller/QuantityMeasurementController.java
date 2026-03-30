package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performEquality(QuantityDTO q1, QuantityDTO q2) {
        System.out.println("Equality = " + service.compare(q1, q2));
    }

    public void performConversion(QuantityDTO q, String unit) {
        QuantityDTO res = service.convert(q, unit);
        System.out.println("Converted = " + res.getValue() + " " + res.getUnit());
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2, String unit) {
        QuantityDTO res = service.add(q1, q2, unit);
        System.out.println("Addition = " + res.getValue() + " " + res.getUnit());
    }
}