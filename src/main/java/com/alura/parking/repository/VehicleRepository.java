package com.alura.parking.repository;

import com.alura.parking.entity.Conductor;
import com.alura.parking.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlate(String plate);

    Page<Vehicle> findAllByConductor(Conductor conductor, Pageable pageable);

    Optional<Vehicle> findByConductorAndPlate(Conductor conductor, String plate);

}
