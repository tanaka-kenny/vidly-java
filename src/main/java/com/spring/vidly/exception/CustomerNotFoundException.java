package com.spring.vidly.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String uuid) {
        super(String.format("The customer with uuid: %s cannot be found", uuid));
    }
}
