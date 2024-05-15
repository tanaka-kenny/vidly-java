package com.spring.vidly.exception;

public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(String uuid) {
        super(String.format("The genre with uuid: %s cannot be found", uuid));
    }
}
