package com.spring.vidly.service;

import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.exception.MovieNotFoundException;
import com.spring.vidly.exception.RentalNotFoundException;

public interface ReturnService {

    RentalDTO createReturn(String rentalUuid, RentalDTO rental) throws RentalNotFoundException, MovieNotFoundException;
}
