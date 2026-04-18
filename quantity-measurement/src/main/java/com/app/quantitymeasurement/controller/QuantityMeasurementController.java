package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/v1/quantities")
@CrossOrigin("*")   //it allow to call API from different port 
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@RequestBody QuantityInputDTO input) {
        return service.add(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/multiply")
    public QuantityMeasurementDTO multiply(@RequestBody QuantityInputDTO input) {
        return service.multiply(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/divide")
    public QuantityMeasurementDTO divide(@RequestBody QuantityInputDTO input) {
        return service.divide(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@RequestBody QuantityInputDTO input) {
        return service.compare(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@RequestBody QuantityInputDTO input) {
        return service.convert(input.getFirstQuantity(), input.getTargetUnit());
    }

    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementDTO> history(@PathVariable String operation) {
        return service.getOperationHistory(operation);
    }

    @GetMapping("/history/type/{type}")
    public List<QuantityMeasurementDTO> type(@PathVariable String type) {
        return service.getMeasurementsByType(type);
    }

    @GetMapping("/count/{operation}")
    public long count(@PathVariable String operation) {
        return service.getOperationCount(operation);
    }

    @GetMapping("/history/error")
    public List<QuantityMeasurementDTO> errors() {
        return service.getErrorHistory();
    }
}