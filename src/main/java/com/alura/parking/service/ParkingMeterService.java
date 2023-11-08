package com.alura.parking.service;

import com.alura.parking.dto.ParkingMeterDTO;
import com.alura.parking.entity.ParkingMeter;
import com.alura.parking.enums.PaymentTimeType;
import com.alura.parking.enums.PaymentType;
import com.alura.parking.exceptions.VehicleNotFoundException;
import com.alura.parking.repository.ConductorRepository;
import com.alura.parking.repository.ParkingMeterRepository;
import com.alura.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingMeterService {

    private final ParkingMeterRepository parkingMeterRepository;
    private final VehicleRepository vehicleRepository;
    private final ConductorRepository conductorRepository;

    @Autowired
    public ParkingMeterService(ParkingMeterRepository parkingMeterRepository,
                               VehicleRepository vehicleRepository,
                               ConductorRepository conductorRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
        this.vehicleRepository = vehicleRepository;
        this.conductorRepository = conductorRepository;
    }

    public void start(ParkingMeterDTO parkingMeterDTO) throws VehicleNotFoundException {
        var vehicle = this.vehicleRepository.findByPlate(parkingMeterDTO.getPlate()).orElseThrow(VehicleNotFoundException::new);
        var conductor = vehicle.getConductor();
        if (PaymentTimeType.FIXED.equals(parkingMeterDTO.getPaymentTimeType())) {
            this.parkingMeterRepository.save(new ParkingMeter(PaymentTimeType.FIXED, vehicle, conductor, parkingMeterDTO.getHours()));
        } else {
            this.parkingMeterRepository.save(new ParkingMeter(PaymentTimeType.PER_HOUR, vehicle, conductor));
        }

    }

    public void finish(Long id, ParkingMeterDTO parkingMeterDTO) throws VehicleNotFoundException {
        var vehicle = this.vehicleRepository.findByPlate(parkingMeterDTO.getPlate()).orElseThrow(VehicleNotFoundException::new);
        var parking = this.parkingMeterRepository.findById(id).orElseThrow();
        this.parkingMeterRepository.save(parking);
    }

    public void pay(Long id, ParkingMeterDTO parkingMeterDTO) {
        var parking = this.parkingMeterRepository.findById(id).orElseThrow();
//        parking.pay(parkingMeterDTO.getPaymentTimeType())
    }

}
