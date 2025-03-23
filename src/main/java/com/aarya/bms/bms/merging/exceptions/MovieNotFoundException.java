package com.aarya.bms.bms.merging.exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String movieId) {
        super("movie with id "+movieId+" not found");
    }
}
