package com.alura.parking.controller;

import com.alura.parking.controller.rest.VehicleInterface;
import com.alura.parking.dto.VehicleDTO;
import com.alura.parking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController implements VehicleInterface {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @Override
    @Cacheable("vehicles")
    public ResponseEntity<?> getVehicles() {
        var vehicles = this.service.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @Override
    public ResponseEntity<?> getVehicleById(int vehicleId) {
        return null;
    }

    @Override
    public ResponseEntity<?> addVehicle(VehicleDTO vehicleDTO) {
        return null;
    }
}
