package com.alura.parking.service;

import com.alura.parking.dto.ParkingMeterDTO;
import com.alura.parking.entity.ParkingMeter;
import com.alura.parking.enums.PaymentTimeType;
import com.alura.parking.enums.PaymentType;
import com.alura.parking.enums.Status;
import com.alura.parking.exceptions.InvalidPaymentException;
import com.alura.parking.exceptions.VehicleNotFoundException;
import com.alura.parking.repository.ConductorRepository;
import com.alura.parking.repository.ParkingMeterRepository;
import com.alura.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> finish(Long id, ParkingMeterDTO parkingMeterDTO) throws VehicleNotFoundException {
        var vehicle = this.vehicleRepository.findByPlate(parkingMeterDTO.getPlate()).orElseThrow(VehicleNotFoundException::new);
        var parking = this.parkingMeterRepository.findByIdAndVehicleAndStatus(id, vehicle, Status.ACTIVE).orElseThrow();
        parking.finish();
        this.parkingMeterRepository.save(parking);

        return getResponse(parking);
    }

    private static HashMap<String, String> getResponse(ParkingMeter parking) {
        HashMap<String, String> response = new HashMap<>();
        response.put("timeParked", parking.getTimeParked().toString());
        response.put("tax", parking.getPaymentTimeType().getName());
        response.put("total", parking.getTotalToPay().toString());
        return response;
    }

    public void pay(Long id, PaymentType paymentType) throws InvalidPaymentException {
        var parking = this.parkingMeterRepository.findByIdAndPaid(id, false).orElseThrow();
        parking.pay(paymentType);
        this.parkingMeterRepository.save(parking);
    }

}
