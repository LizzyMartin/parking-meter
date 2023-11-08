package com.alura.parking.enums;

import lombok.Getter;

@Getter
public enum PaymentType {
    CREDIT_CARD("creditCard"), DEBIT("debit"), PIX("pix");

    PaymentType(String name) {
    }
}
