package com.alura.parking.service;

import com.alura.parking.dto.VehicleDTO;
import com.alura.parking.entity.Vehicle;
import com.alura.parking.exceptions.ConductorNotFoundException;
import com.alura.parking.exceptions.VehicleNotFoundException;
import com.alura.parking.mapper.VehicleMapper;
import com.alura.parking.repository.ConductorRepository;
import com.alura.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ConductorRepository conductorRepository;
    private final VehicleMapper mapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository
            , ConductorRepository conductorRepository
            , VehicleMapper mapper) {
        this.vehicleRepository = vehicleRepository;
        this.conductorRepository = conductorRepository;
        this.mapper = mapper;
    }

    public List<Vehicle> getVehicles() {
        return this.vehicleRepository.findAll();
    }

    public List<Vehicle> getVehiclesByConductorCpf(String cpf, Pageable pageable) throws ConductorNotFoundException {
        var conductor = this.conductorRepository.findByCpf(cpf).orElseThrow(ConductorNotFoundException::new);
        var vehiclesConductor = this.vehicleRepository.findAllByConductor(conductor, pageable);
        return vehiclesConductor.getContent();
    }

    public Vehicle getVehicleByPlate(String plate) throws VehicleNotFoundException {
        return this.vehicleRepository.findByPlate(plate).orElseThrow(VehicleNotFoundException::new);
    }

    public void addVehicle(VehicleDTO vehicleDTO) throws ConductorNotFoundException {
        if (this.conductorRepository.findById(Long.getLong(vehicleDTO.getConductorId())).isEmpty()) {
            throw new ConductorNotFoundException();
        }
        this.vehicleRepository.save(this.mapper.vehicleDTOToVehicle(vehicleDTO));
    }

    public void updateVehicle(VehicleDTO vehicleDTO) throws ConductorNotFoundException, VehicleNotFoundException {
        var conductor = this.conductorRepository.findById(Long.getLong(vehicleDTO.getConductorId())).orElseThrow(ConductorNotFoundException::new);
        var vehicle = this.vehicleRepository.findByConductorAndPlate(conductor, vehicleDTO.getPlate()).orElseThrow(VehicleNotFoundException::new);
        updateIfNotNullOrBlank(vehicleDTO.getPlate(), vehicle::setPlate);
        updateIfNotNullOrBlank(vehicleDTO.getBrand(), vehicle::setBrand);
        this.vehicleRepository.save(vehicle);
    }

    public void removeVehicle(Long conductorId, String plate) throws ConductorNotFoundException, VehicleNotFoundException {
        var conductor = this.conductorRepository.findById(conductorId).orElseThrow(ConductorNotFoundException::new);
        var vehicle = this.vehicleRepository.findByConductorAndPlate(conductor, plate).orElseThrow(VehicleNotFoundException::new);
        this.vehicleRepository.delete(vehicle);
    }

    private void updateIfNotNullOrBlank(String value, Consumer<String> setter) {
        if (value != null && !value.isBlank()) {
            setter.accept(value);
        }
    }

}
