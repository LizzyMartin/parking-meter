package com.alura.parking.controller.rest;

import com.alura.parking.dto.VehicleDTO;
import com.alura.parking.entity.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Vehicles", description = "Vehicles management")
@RequestMapping("/vehicles")
public interface VehicleInterface {

    @GetMapping
    @Operation(description = "Get all vehicles")
    @ApiResponse(responseCode = "200", description = "All vehicles listed successfully!")
    ResponseEntity<List<Vehicle>> getVehicles();

    @GetMapping("/plate/{plate}")
    @Operation(description = "Get a specific vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle listed successfully!")
    ResponseEntity<Vehicle> getVehicleByPlate(@PathVariable String plate);

    @GetMapping("/conductor/{cpf}")
    @Operation(description = "Get all vehicles from conductor")
    @ApiResponse(responseCode = "200", description = "Vehicle listed successfully!")
    ResponseEntity<List<Vehicle>> getVehicleByConductor(@PathVariable String cpf, Pageable pageable);

    @PostMapping
    @Operation(description = "Add a new vehicle to database")
    @ApiResponse(responseCode = "200", description = "Vehicle added successfully!")
    ResponseEntity<?> addVehicle(@RequestBody @Valid VehicleDTO vehicleDTO);

    @PutMapping
    @Operation(description = "Update vehicle to database")
    @ApiResponse(responseCode = "201", description = "Vehicle updated successfully!")
    ResponseEntity<?> updateVehicle(@RequestBody @Valid VehicleDTO vehicleDTO);

    @DeleteMapping
    @Operation(description = "Delete vehicle to database")
    @ApiResponse(responseCode = "200", description = "Vehicle deleted successfully!")
    ResponseEntity<?> deleteVehicle(@RequestBody @Valid VehicleDTO vehicleDTO);
}
