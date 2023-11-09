package com.alura.parking.controller;

import com.alura.parking.controller.rest.ParkingMeterInterface;
import com.alura.parking.dto.ParkingMeterDTO;
import com.alura.parking.enums.PaymentType;
import com.alura.parking.exceptions.VehicleNotFoundException;
import com.alura.parking.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingMeterController implements ParkingMeterInterface {

    private final ParkingMeterService service;

    @Autowired
    public ParkingMeterController(ParkingMeterService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getByCpf(String cpf) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> start(ParkingMeterDTO parkingMeterDTO) {
        try {
            this.service.start(parkingMeterDTO);
            return ResponseEntity.ok().build();
        } catch (VehicleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> finish(Long id, ParkingMeterDTO parkingMeterDTO) {
        try {
            var receipt = this.service.finish(id, parkingMeterDTO);
            return ResponseEntity.ok(receipt);
        } catch (VehicleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> pay(Long id, PaymentType paymentType) {
        try {
            this.service.pay(id, paymentType);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
