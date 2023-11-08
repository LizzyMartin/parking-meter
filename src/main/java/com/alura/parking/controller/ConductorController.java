package com.alura.parking.controller;

import com.alura.parking.controller.rest.ConductorInterface;
import com.alura.parking.dto.ConductorDTO;
import com.alura.parking.exceptions.ConductorNotFoundException;
import com.alura.parking.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConductorController implements ConductorInterface {

    private final ConductorService service;

    @Autowired
    public ConductorController(ConductorService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            var conductor = this.service.getById(id);
            return ResponseEntity.ok(conductor);
        } catch (ConductorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> getByCpf(String cpf) {
        try {
            var conductor = this.service.getByCpf(cpf);
            return ResponseEntity.ok(conductor);
        } catch (ConductorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> deleteByCpf(String cpf) {
        try {
            this.service.removeConductor(cpf);
            return ResponseEntity.ok().build();
        } catch (ConductorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(this.service.getAllConductor());
    }

    @Override
    public ResponseEntity<?> updateConductor(ConductorDTO conductorDTO) {
        try {
            this.service.updateConductor(conductorDTO);
            return ResponseEntity.ok().build();
        } catch (ConductorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> addConductor(ConductorDTO conductorDTO) {
        return ResponseEntity.ok().body(this.service.addConductor(conductorDTO));
    }
}
