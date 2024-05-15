package com.spring.vidly.service;

import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.exception.RentalNotFoundException;

import java.util.List;

public interface RentalService {

    List<RentalDTO> getAll();

    RentalDTO createRental(RentalDTO rental) throws RentalNotFoundException;
}
