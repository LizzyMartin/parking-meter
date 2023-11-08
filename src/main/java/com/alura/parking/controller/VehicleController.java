package com.alura.parking.controller;

import com.alura.parking.controller.rest.VehicleInterface;
import com.alura.parking.dto.VehicleDTO;
import com.alura.parking.entity.Vehicle;
import com.alura.parking.exceptions.ConductorNotFoundException;
import com.alura.parking.exceptions.VehicleNotFoundException;
import com.alura.parking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController implements VehicleInterface {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @Override
    @Cacheable("vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        var vehicles = this.service.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @Override
    public ResponseEntity<Vehicle> getVehicleByPlate(String plate) {
        try {
            var vehicle = this.service.getVehicleByPlate(plate);
            return ResponseEntity.ok().body(vehicle);
        } catch (VehicleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Vehicle>> getVehicleByConductor(String cpf, Pageable pageable) {
        try {
            this.service.getVehiclesByConductorCpf(cpf, pageable);
            return ResponseEntity.ok().build();
        } catch (ConductorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> addVehicle(VehicleDTO vehicleDTO) {
        try {
            this.service.addVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConductorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> updateVehicle(VehicleDTO vehicleDTO) {
        try {
            this.service.updateVehicle(vehicleDTO);
            return ResponseEntity.ok().build();
        } catch (ConductorNotFoundException | VehicleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> deleteVehicle(VehicleDTO vehicleDTO) {
        try {
            this.service.removeVehicle(Long.getLong(vehicleDTO.getConductorId()), vehicleDTO.getPlate());
            return ResponseEntity.ok().build();
        } catch (ConductorNotFoundException | VehicleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
