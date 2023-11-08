package com.alura.parking.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public enum PaymentTimeType {
    FIXED("fixed", BigDecimal.valueOf(50.0), List.of(PaymentType.CREDIT_CARD, PaymentType.DEBIT, PaymentType.PIX)),
    PER_HOUR("per hour", BigDecimal.valueOf(15.0), List.of(PaymentType.DEBIT, PaymentType.CREDIT_CARD));

    private final String name;
    private final BigDecimal price;
    private final List<PaymentType> availablePaymentTypes;

    PaymentTimeType(String name, BigDecimal price, List<PaymentType> availablePaymentTypes) {
        this.name = name;
        this.price = price;
        this.availablePaymentTypes = availablePaymentTypes;
    }

}
