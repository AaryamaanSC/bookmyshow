package com.aarya.bms.bms.merging.exceptions;

public class TheaterNotFoundException extends RuntimeException{
    public TheaterNotFoundException(String theatreId){
        super("Theater with id "+theatreId+" not found");
    }
}
