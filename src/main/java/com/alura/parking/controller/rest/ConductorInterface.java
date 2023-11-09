package com.alura.parking.controller.rest;

import com.alura.parking.dto.ConductorDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Conductor", description = "Conductor management")
@RequestMapping("/conductor")
public interface ConductorInterface {

    @GetMapping("/id/{id}")
    ResponseEntity<?> getById(@PathVariable Long id);

    @GetMapping("/{cpf}")
    ResponseEntity<?> getByCpf(String cpf);

    @DeleteMapping("/{cpf}")
    ResponseEntity<?> deleteByCpf(String cpf);

    @GetMapping
    ResponseEntity<?> getAll();

    @PutMapping
    ResponseEntity<?> updateConductor(ConductorDTO conductorDTO);

    @PostMapping
    ResponseEntity<?> addConductor(@RequestBody @Valid ConductorDTO conductorDTO);

}
