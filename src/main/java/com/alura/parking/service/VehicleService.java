package com.alura.parking.service;

import com.alura.parking.dto.VehicleDTO;
import com.alura.parking.entity.Vehicle;
import com.alura.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    @Autowired
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> getVehicles() {
        return this.repository.findAll();
    }

    public void addVehicle(VehicleDTO vehicleDTO) {

    }

}
