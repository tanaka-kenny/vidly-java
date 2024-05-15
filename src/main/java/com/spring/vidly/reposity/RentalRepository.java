package com.spring.vidly.reposity;

import com.spring.vidly.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Rental findRentalByUuid(String uuid);
}
