package com.alura.parking.controller.rest;

import com.alura.parking.dto.VehicleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Vehicles", description = "Vehicles management")
@RequestMapping("/vehicles")
public interface VehicleInterface {

    @GetMapping
    @Operation(description = "Get all vehicles")
    @ApiResponse(responseCode = "200", description = "All vehicles listed successfully!")
    ResponseEntity<?> getVehicles();

    @GetMapping("/{vehicleId}")
    @Operation(description = "Get a specific vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle listed successfully!")
    ResponseEntity<?> getVehicleById(@PathVariable int vehicleId);

    @PostMapping
    @Operation(description = "Add a new vehicle to database")
    @ApiResponse(responseCode = "200", description = "Vehicle added successfully!")
    ResponseEntity<?> addVehicle(@RequestBody @Valid VehicleDTO vehicleDTO);
}
