package com.spring.vidly.exception;

public class LowMovieStockException extends RuntimeException{
    public LowMovieStockException(String uuid) {
        super(String.format("The movie with uuid: %s is low on stock", uuid));
    }
}
