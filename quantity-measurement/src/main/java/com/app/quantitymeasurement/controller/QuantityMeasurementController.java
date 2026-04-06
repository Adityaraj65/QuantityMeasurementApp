package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@Valid @RequestBody QuantityInputDTO input) {
        return service.compare(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@Valid @RequestBody QuantityInputDTO input) {
        return service.add(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@Valid @RequestBody QuantityInputDTO input) {
        return service.convert(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );
    }

    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementDTO> getByOperation(@PathVariable String operation) {
        return service.getByOperation(operation);
    }

    @GetMapping("/history/errored")
    public List<QuantityMeasurementDTO> getErrors() {
        return service.getErrorHistory();
    }
}