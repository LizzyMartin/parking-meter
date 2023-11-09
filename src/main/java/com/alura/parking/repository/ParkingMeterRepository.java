package com.alura.parking.repository;

import com.alura.parking.entity.ParkingMeter;
import com.alura.parking.entity.Vehicle;
import com.alura.parking.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {

    Optional<ParkingMeter> findByIdAndVehicleAndStatus(Long id, Vehicle vehicle, Status status);

    Optional<ParkingMeter> findByIdAndPaid(Long id, boolean paid);

}
