package com.spring.vidly.controller;

import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalController {

    private RentalService rentalService;

    @GetMapping("/")
    public List<RentalDTO> getAll() {
        return rentalService.getAll();
    }

    @PostMapping("/")
    public RentalDTO createRental(@RequestBody RentalDTO rental) throws Exception {
        return rentalService.createRental(rental);
    }
}
