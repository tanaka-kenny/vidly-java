package com.spring.vidly.service;


import com.spring.vidly.dto.MovieDTO;
import com.spring.vidly.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    List<MovieDTO> getAll();

    MovieDTO getMovie(String uuid) throws MovieNotFoundException;

    MovieDTO createMovie(MovieDTO movie);

    MovieDTO updateMovie(String movieUuid,MovieDTO movieDTO) throws MovieNotFoundException;

    void deleteMovie(String uuid);
}
