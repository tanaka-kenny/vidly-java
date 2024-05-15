package com.spring.vidly.dto;

import com.spring.vidly.domain.Genre;
import org.apache.commons.lang3.ObjectUtils;

import java.util.UUID;

public record GenreDTO(String uuid, String name) {

    public static GenreDTO createFromGenre(Genre genre) {
        return new GenreDTO(genre.getUuid(), genre.getName());
    }

    public Genre convertDTO() {
        var genre = new Genre();

        genre.setUuid(ObjectUtils.isNotEmpty(this.uuid()) ? uuid() : UUID.randomUUID().toString());
        genre.setName(name());

        return genre;
    }
}
