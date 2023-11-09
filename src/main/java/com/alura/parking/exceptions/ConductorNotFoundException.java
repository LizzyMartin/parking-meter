package com.alura.parking.exceptions;

public class ConductorNotFoundException extends Exception {

    public ConductorNotFoundException() {
        super();
    }

    public ConductorNotFoundException(String message) {
        super(message);
    }
}
