package com.alura.parking.repository;

import com.alura.parking.entity.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Long> {
}
