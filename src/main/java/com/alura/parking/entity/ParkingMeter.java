package com.alura.parking.entity;

import com.alura.parking.enums.PaymentTimeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime = LocalDateTime.now();

    private LocalDateTime finishTime;

    private PaymentTimeType paymentTimeType;

    @OneToOne
    private Vehicle vehicle;

    @OneToOne
    private Conductor conductor;



}
