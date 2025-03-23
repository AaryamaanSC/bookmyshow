package com.aarya.bms.bms.merging.exceptions;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String showId) {
        super("show with id "+showId+" not found" );
    }
}
