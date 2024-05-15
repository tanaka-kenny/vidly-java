package com.spring.vidly.reposity;

import com.spring.vidly.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findMovieByUuid(String uuid);

    void deleteMovieByUuid(String uuid);
}
