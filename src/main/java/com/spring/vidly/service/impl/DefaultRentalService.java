package com.spring.vidly.service.impl;

import com.spring.vidly.domain.Rental;
import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.exception.CustomerNotFoundException;
import com.spring.vidly.exception.LowMovieStockException;
import com.spring.vidly.exception.MovieNotFoundException;
import com.spring.vidly.exception.RentalNotFoundException;
import com.spring.vidly.reposity.CustomerRepository;
import com.spring.vidly.reposity.MovieRepository;
import com.spring.vidly.reposity.RentalRepository;
import com.spring.vidly.service.RentalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultRentalService implements RentalService {

    private RentalRepository rentalRepository;
    private CustomerRepository customerRepository;
    private MovieRepository movieRepository;

    @Override
    public List<RentalDTO> getAll() {
        var rentals = rentalRepository.findAll();
        return rentals.stream().map(RentalDTO::createFromRental).toList();
    }

    @Override
    @Transactional
    public RentalDTO createRental(RentalDTO rentalDTO) {
        // find the customer,
        if (ObjectUtils.isEmpty(rentalDTO.customerUuid())) throw new CustomerNotFoundException("null");

        var customer = customerRepository.findCustomerByUuid(rentalDTO.customerUuid());
        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException(rentalDTO.customerUuid());
        }
        // find the movie
        if (ObjectUtils.isEmpty(rentalDTO.movieUuid())) throw new MovieNotFoundException("null");

        var movie = movieRepository.findMovieByUuid(rentalDTO.movieUuid());
        if(Objects.isNull(movie)) throw new MovieNotFoundException(rentalDTO.movieUuid());

        if (movie.getNumberInStock() == 0) throw new LowMovieStockException(movie.getUuid());

        // create rental
        var rental = new Rental();
        rental.setUuid(UUID.randomUUID().toString());
        rental.setDateOut(LocalDate.now());
        rental.setMovie(movie);
        rental.setCustomer(customer);

        movie.setNumberInStock(movie.getNumberInStock() - 1);

        rentalRepository.save(rental);
        movieRepository.save(movie);

        return RentalDTO.createFromRental(rental);
    }
}
