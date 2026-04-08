package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtract(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/multiply")
    public ResponseEntity<QuantityMeasurementDTO> multiply(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.multiply(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divide(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convert(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.convert(input.getThisQuantityDTO(), input.getTargetQuantityDTO()));
    }

    // ================= HISTORY =================

    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getByOperation(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationHistory(operation));
    }

    @GetMapping("/history/type/{type}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getMeasurementsByType(type));
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationCount(operation));
    }

    @GetMapping("/history/errors")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrors() {
        return ResponseEntity.ok(service.getErrorHistory());
    }
}