package com.spring.vidly.service.impl;

import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.exception.MovieNotFoundException;
import com.spring.vidly.exception.RentalNotFoundException;
import com.spring.vidly.reposity.MovieRepository;
import com.spring.vidly.reposity.RentalRepository;
import com.spring.vidly.service.ReturnService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

// this module uses the Test Driven Development approach
@Service
@AllArgsConstructor
public class DefaultReturnService implements ReturnService {

    private RentalRepository rentalRepository;

    @Transactional
    @Override
    public RentalDTO createReturn(String rentalUuid, RentalDTO rentalDTO) {

        var rental = rentalRepository.findRentalByUuid(rentalUuid);
        if (Objects.isNull(rental)) throw new RentalNotFoundException(rentalUuid);

        rental.setDateReturned(LocalDate.now());

        var rentalDays = Period.between(rental.getDateReturned(), rental.getDateOut()).getDays();
        rental.setRentalFee((double) (rentalDays * rental.getMovie().getDailyRentalRate()));

        rentalRepository.save(rental);
        return RentalDTO.createFromRental(rental);
    }
}
