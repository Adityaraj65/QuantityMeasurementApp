package com.app.quantity_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantity_service.dto.QuantityMeasurementDTO;
import com.app.quantity_service.model.QuantityInputDTO;
import com.app.quantity_service.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // ✅ PUBLIC APIs

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@RequestBody QuantityInputDTO input) {
        return service.add(input.getFirstQuantity(), input.getSecondQuantity(), null);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(input.getFirstQuantity(), input.getSecondQuantity(), null);
    }

    @PostMapping("/multiply")
    public QuantityMeasurementDTO multiply(@RequestBody QuantityInputDTO input) {
        return service.multiply(input.getFirstQuantity(), input.getSecondQuantity(), null);
    }

    @PostMapping("/divide")
    public QuantityMeasurementDTO divide(@RequestBody QuantityInputDTO input) {
        return service.divide(input.getFirstQuantity(), input.getSecondQuantity(), null);
    }

    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@RequestBody QuantityInputDTO input) {
        return service.compare(input.getFirstQuantity(), input.getSecondQuantity(), null);
    }

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@RequestBody QuantityInputDTO input) {
        return service.convert(input.getFirstQuantity(), input.getTargetUnit(), null);
    }

    // 🔐 PROTECTED API
    @GetMapping("/history/type/{type}")
    public List<QuantityMeasurementDTO> getByType(
            @PathVariable String type,
            @RequestHeader("user-email") String email) {

        return service.getMeasurementsByType(type, email);
    }

    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementDTO> history(
            @PathVariable String operation,
            @RequestHeader("user-email") String email) {

        return service.getOperationHistory(operation, email);
    }
}