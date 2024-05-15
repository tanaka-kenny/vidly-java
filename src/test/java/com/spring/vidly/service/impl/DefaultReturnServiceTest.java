package com.spring.vidly.service.impl;

import com.spring.vidly.domain.Genre;
import com.spring.vidly.dto.CustomerDTO;
import com.spring.vidly.dto.GenreDTO;
import com.spring.vidly.dto.MovieDTO;
import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.reposity.MovieRepository;
import com.spring.vidly.reposity.RentalRepository;
import com.spring.vidly.service.ReturnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultReturnServiceTest {

    private ReturnService returnService;
    private RentalDTO rentalDTO;
    private MovieDTO movieDTO;


    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        returnService = new DefaultReturnService(rentalRepository);
        movieDTO = new MovieDTO("415449ba-f04a-4841-9fe7-007aa027e23d", "Hunger Games", 10, 2, "");
        CustomerDTO customerDTO = new CustomerDTO("1a054bd9-f1fb-4b7a-9299-86e2c227569b", false, "Jeff Jones", "+0000000000");
        rentalDTO = new RentalDTO(
                "0448de70-3cf0-4260-8567-039c99cef45c", LocalDate.now().minusDays(7), null, null, "movieDTO", "");

    }

    @Test
    void createReturn_ShouldSetRentalReturnDate() {

        when(rentalRepository.findRentalByUuid(rentalDTO.uuid())).thenReturn(rentalDTO.convertDto());
        when(movieRepository.findMovieByUuid(movieDTO.uuid())).thenReturn(movieDTO.convertDTO(null));

        var result = returnService.
                createReturn(rentalDTO.uuid(),rentalDTO);
        var period = Period.between(LocalDate.now(), result.dateReturned());

        assertNotNull(result.dateReturned());
        assertEquals(0, period.getDays());
    }

    @Test
    void createReturn_ShouldSetTheRentalFee() throws Exception {
        when(rentalRepository.findRentalByUuid(rentalDTO.uuid())).thenReturn(rentalDTO.convertDto());
        when(movieRepository.findMovieByUuid(eq(movieDTO.uuid()))).thenReturn(movieDTO.convertDTO(null));

        var result = returnService.createReturn(rentalDTO.uuid(), rentalDTO);

        assertNotNull(result.rentalFee());

    }
}