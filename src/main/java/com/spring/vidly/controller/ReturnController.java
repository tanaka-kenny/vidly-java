package com.spring.vidly.controller;

import com.spring.vidly.dto.RentalDTO;
import com.spring.vidly.service.ReturnService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/returns")
@AllArgsConstructor
public class ReturnController {

    private ReturnService returnService;

    @PutMapping("/{rentalUuid}")
    public RentalDTO createReturn(@PathVariable String rentalUuid,@RequestBody RentalDTO rental) throws Exception {
        return returnService.createReturn(rentalUuid, rental);
    }
}
