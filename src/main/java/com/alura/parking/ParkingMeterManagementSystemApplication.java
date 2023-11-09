package com.alura.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ParkingMeterManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingMeterManagementSystemApplication.class, args);
    }

}
