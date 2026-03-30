package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityRequest;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    @Autowired
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    // ================= COMPARE =================
    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityRequest request) {
        return service.compare(request.getQ1(), request.getQ2());
    }
    // ================= CONVERT =================
    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit) {
        return service.convert(quantity, targetUnit);
    }

    // ================= ADD =================
    @PostMapping("/add")
    public QuantityDTO add(@RequestBody QuantityDTO q1,
                           @RequestBody QuantityDTO q2,
                           @RequestParam String targetUnit) {
        return service.add(q1, q2, targetUnit);
    }
}