package com.spring.vidly.exception;

public class RentalNotFoundException extends RuntimeException{
    public RentalNotFoundException(String uuid) {
        super(String.format("The rental with uuid: %s cannot be found", uuid));
    }
}
