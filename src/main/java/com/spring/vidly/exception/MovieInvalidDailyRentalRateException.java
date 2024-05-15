package com.spring.vidly.exception;

public class MovieInvalidDailyRentalRateException extends RuntimeException{
    public MovieInvalidDailyRentalRateException(Integer expected, Integer given) {
        super(String.format("Expected dailyRentalRate to be no less than %d but received %d", expected, given));
    }
}
