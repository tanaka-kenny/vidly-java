package com.spring.vidly.exception;

public class MovieInvalidNumberInStockException extends RuntimeException{
    public MovieInvalidNumberInStockException(Integer expected, Integer given) {
        super(String.format("Expected numberInStock to be no less that: %d for  but received %d", expected, given));
    }
}
