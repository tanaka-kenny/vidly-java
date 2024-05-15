package com.spring.vidly.dto;

import com.spring.vidly.domain.Customer;
import com.spring.vidly.domain.Movie;
import com.spring.vidly.domain.Rental;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.util.UUID;

public record RentalDTO(String uuid, LocalDate dateOut, LocalDate dateReturned, Double rentalFee, String movieUuid,
                        String customerUuid) {

    public static RentalDTO createFromRental(Rental rental) {
        return new RentalDTO(rental.getUuid(), rental.getDateOut(), rental.getDateReturned(), rental.getRentalFee(),
                rental.getMovie().getUuid(), rental.getCustomer().getUuid());
    }

    public Rental convertDto() {
        var rental = new Rental();
        rental.setUuid(ObjectUtils.isNotEmpty(uuid()) ? uuid() : UUID.randomUUID().toString());
        rental.setDateOut(dateOut());
        rental.setDateReturned(dateReturned());
        rental.setRentalFee(rentalFee());

        var movie = new Movie();
        movie.setUuid(movieUuid());
        rental.setMovie(movie); // todo: fix this and pass a valid genre

        var customer = new Customer();
        customer.setUuid(customerUuid());
        rental.setCustomer(customer);

        return rental;
    }
}
