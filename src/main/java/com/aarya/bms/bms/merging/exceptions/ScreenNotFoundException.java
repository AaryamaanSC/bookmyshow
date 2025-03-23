package com.aarya.bms.bms.merging.exceptions;

public class ScreenNotFoundException extends RuntimeException{
    public ScreenNotFoundException(String screenId) {
        super("Screen with id "+screenId+ " not found");
    }
}
