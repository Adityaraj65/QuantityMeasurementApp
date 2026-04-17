package com.app.quantity_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@RequestBody QuantityInputDTO input, @RequestHeader("user-email") String email) {
        return service.add(input.getFirstQuantity(), input.getSecondQuantity(), email);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@RequestBody QuantityInputDTO input, @RequestHeader("user-email") String email) {
        return service.subtract(input.getFirstQuantity(), input.getSecondQuantity(), email);
    }

    @PostMapping("/multiply")
    public QuantityMeasurementDTO multiply(@RequestBody QuantityInputDTO input, @RequestHeader("user-email") String email) {
        return service.multiply(input.getFirstQuantity(), input.getSecondQuantity(), email);
    }

    @PostMapping("/divide")
    public QuantityMeasurementDTO divide(@RequestBody QuantityInputDTO input, @RequestHeader("user-email") String email) {
        return service.divide(input.getFirstQuantity(), input.getSecondQuantity(), email);
    }

    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@RequestBody QuantityInputDTO input, @RequestHeader("user-email") String email) {
        return service.compare(input.getFirstQuantity(), input.getSecondQuantity(), email);
    }

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@RequestBody QuantityInputDTO input, @RequestHeader("user-email") String email) {
        return service.convert(input.getFirstQuantity(), input.getTargetUnit(), email);
    }

    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementDTO> history(@PathVariable String operation, @RequestHeader("user-email") String email) {
        return service.getOperationHistory(operation, email);
    }

    @GetMapping("/history/type/{type}")
    public List<QuantityMeasurementDTO> type(@PathVariable String type, @RequestHeader("user-email") String email) {
        return service.getMeasurementsByType(type, email);
    }

    @GetMapping("/count/{operation}")
    public long count(@PathVariable String operation, @RequestHeader("user-email") String email) {
        return service.getOperationCount(operation, email);
    }

    @GetMapping("/history/error")
    public List<QuantityMeasurementDTO> errors(@RequestHeader("user-email") String email) {
        return service.getErrorHistory(email);
    }
}