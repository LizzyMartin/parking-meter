package com.alura.parking.exceptions;

public class InvalidPaymentException extends Exception {

    public InvalidPaymentException() {
        super();
    }

    public InvalidPaymentException(String message) { super(message); }
}
