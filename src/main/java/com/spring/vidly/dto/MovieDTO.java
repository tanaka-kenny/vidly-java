package com.spring.vidly.dto;

import com.spring.vidly.domain.Genre;
import com.spring.vidly.domain.Movie;
import org.apache.commons.lang3.ObjectUtils;

import java.util.UUID;

public record MovieDTO(String uuid, String title, Integer numberInStock, Integer dailyRentalRate, String genreUuid) {

    public static MovieDTO createFromMovie(Movie movie) {
        return new MovieDTO(
                movie.getUuid(),
                movie.getTitle(),
                movie.getNumberInStock(),
                movie.getDailyRentalRate(),
                movie.getGenre().getUuid());
    }

    public Movie convertDTO(Genre genre) {
        var movie = new Movie();
        movie.setUuid(ObjectUtils.isNotEmpty(uuid()) ? uuid() : UUID.randomUUID().toString());
        movie.setTitle(title());
        movie.setNumberInStock(numberInStock());
        movie.setDailyRentalRate(dailyRentalRate());
        movie.setGenre(genre);

        return movie;
    }
}
