package com.aarya.bms.bms.merging.exceptions;

public class ScreenSeatsNotFoundException extends RuntimeException{
    public ScreenSeatsNotFoundException(String screenSeatsId) {
        super("ScreenSeats with id "+ screenSeatsId+" not found");
    }
}
