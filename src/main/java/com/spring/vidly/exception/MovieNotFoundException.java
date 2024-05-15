package com.spring.vidly.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String uuid) {
        super(String.format("The movie with uuid: %s cannot be found", uuid));
    }
}
