package com.alura.parking.dto;

import com.alura.parking.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {

    private String id;

    private Double totalToPay;

    private String timeParked;

    private VehicleDTO vehicle;

    private List<PaymentType> availablePaymentTypes;

}
