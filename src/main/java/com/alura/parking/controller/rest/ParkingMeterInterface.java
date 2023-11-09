package com.alura.parking.controller.rest;

import com.alura.parking.dto.ParkingMeterDTO;
import com.alura.parking.enums.PaymentType;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/parking")
public interface ParkingMeterInterface {

    @GetMapping("/cpf/{cpf}")
    ResponseEntity<?> getByCpf(@PathVariable String cpf);

    @PostMapping("/start")
    ResponseEntity<?> start(@RequestBody @Valid ParkingMeterDTO parkingMeterDTO);

    @PostMapping("/finish/{id}")
    ResponseEntity<?> finish(@PathVariable Long id, @RequestBody @Valid ParkingMeterDTO parkingMeterDTO);

    @PostMapping("/pay/{id}")
    ResponseEntity<?> pay(@PathVariable Long id, @RequestBody @Valid PaymentType paymentType);
}
