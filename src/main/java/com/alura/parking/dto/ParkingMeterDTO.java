package com.alura.parking.dto;

import com.alura.parking.enums.PaymentTimeType;
import com.alura.parking.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingMeterDTO {

    private Long hours;

    @NotBlank
    private String plate;

    @NotNull
    private PaymentTimeType paymentTimeType;

    @NotNull
    private PaymentType paymentType;
}
