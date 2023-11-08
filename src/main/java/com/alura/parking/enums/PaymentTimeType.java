package com.alura.parking.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum PaymentTimeType {
    FIXED("fixed", 50.0, List.of(PaymentType.CREDIT_CARD, PaymentType.DEBIT, PaymentType.PIX)),
    PER_HOUR("per hour", 15.0, List.of(PaymentType.DEBIT, PaymentType.CREDIT_CARD));

    PaymentTimeType(String name, Double price, List<PaymentType> availablePaymentType) {
    }
}
