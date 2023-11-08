package com.alura.parking.exceptions;

public class VehicleNotFoundException extends Exception {

    public VehicleNotFoundException() {
        super();
    }

    public VehicleNotFoundException(String message) { super(message); }
}
