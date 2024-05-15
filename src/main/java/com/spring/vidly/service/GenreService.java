package com.spring.vidly.service;

import com.spring.vidly.dto.GenreDTO;
import com.spring.vidly.exception.GenreNotFoundException;

import java.util.List;

public interface GenreService {

    List<GenreDTO> getAll();

    GenreDTO getGenre(String uuid) throws GenreNotFoundException;

    GenreDTO createGenre(GenreDTO genre);

    GenreDTO updateGenre(String genreUuid, GenreDTO genreDTO) throws GenreNotFoundException;

    void deleteGenre(String uuid);
}
