package com.aarya.bms.bms.merging.exceptions;

public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String userId){
            super("User with id "+userId+" not found");
        }
}
