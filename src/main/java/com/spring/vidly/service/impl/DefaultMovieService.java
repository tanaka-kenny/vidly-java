package com.spring.vidly.service.impl;

import com.spring.vidly.domain.Movie;
import com.spring.vidly.dto.MovieDTO;
import com.spring.vidly.exception.GenreNotFoundException;
import com.spring.vidly.exception.MovieInvalidDailyRentalRateException;
import com.spring.vidly.exception.MovieInvalidNumberInStockException;
import com.spring.vidly.exception.MovieNotFoundException;
import com.spring.vidly.reposity.GenreRepository;
import com.spring.vidly.reposity.MovieRepository;
import com.spring.vidly.service.MovieService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultMovieService implements MovieService {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    @Override
    public List<MovieDTO> getAll() {
        var movies = movieRepository.findAll();
        return movies.stream().map(MovieDTO::createFromMovie).toList();
    }

    @Override
    public MovieDTO getMovie(String uuid) throws MovieNotFoundException {
        var movie = movieRepository.findMovieByUuid(uuid);
        if(Objects.isNull(movie)) {
            throw new MovieNotFoundException(uuid);
        }
        return MovieDTO.createFromMovie(movie);
    }

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        var movie = new Movie();

        var genre = genreRepository.findGenreByUuid(movieDTO.genreUuid());
        if(Objects.isNull(genre)) {
            throw new GenreNotFoundException(movieDTO.genreUuid());
        }

        movie.setGenre(genre);
        movie.setUuid(UUID.randomUUID().toString());
        verifyDtoAndSetMovie(movieDTO, movie);

        movieRepository.save(movie);
        return MovieDTO.createFromMovie(movie);
    }

    @Override
    public MovieDTO updateMovie(String movieUuid,MovieDTO movieDTO) throws MovieNotFoundException {
        var movie = movieRepository.findMovieByUuid(movieUuid);
        if(Objects.isNull(movie)) {
            throw new MovieNotFoundException(movieUuid);
        }

        if(ObjectUtils.isNotEmpty(movieDTO.genreUuid())) {
            var genre = genreRepository.findGenreByUuid(movieDTO.genreUuid());
            if(Objects.isNull(genre)) {
                throw new GenreNotFoundException(movieDTO.genreUuid());
            }
            movie.setGenre(genre);
        }

        verifyDtoAndSetMovie(movieDTO, movie);

        movieRepository.save(movie);
        return MovieDTO.createFromMovie(movie);
    }

    @Override
    public void deleteMovie(String uuid) {
        movieRepository.deleteMovieByUuid(uuid);
    }

    private void verifyDtoAndSetMovie(MovieDTO movieDTO, Movie movie) {
        movie.setTitle(ObjectUtils.isNotEmpty(movieDTO.title()) ? movieDTO.title(): movie.getTitle());
        if ( ObjectUtils.isNotEmpty(movieDTO.numberInStock())) {
            if ((movieDTO.numberInStock() < 0)) {
                throw new MovieInvalidNumberInStockException(0, movieDTO.numberInStock());
            }
            movie.setNumberInStock(movieDTO.numberInStock());
        }

        if (ObjectUtils.isNotEmpty(movieDTO.dailyRentalRate())) {
            if ((movieDTO.dailyRentalRate() < 1)) {
                throw new MovieInvalidDailyRentalRateException(1, movieDTO.dailyRentalRate());
            }
            movie.setDailyRentalRate(movieDTO.dailyRentalRate());
        }

    }
}
