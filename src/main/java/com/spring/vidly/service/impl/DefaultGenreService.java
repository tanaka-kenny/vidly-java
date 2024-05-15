package com.spring.vidly.service.impl;

import com.spring.vidly.dto.CustomerDTO;
import com.spring.vidly.dto.GenreDTO;
import com.spring.vidly.exception.GenreNotFoundException;
import com.spring.vidly.reposity.GenreRepository;
import com.spring.vidly.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultGenreService implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> getAll() {
        var genres = genreRepository.findAll();
        return genres.stream().map(GenreDTO::createFromGenre).toList();
    }

    @Override
    public GenreDTO getGenre(String uuid) throws GenreNotFoundException {
        var genre = genreRepository.findGenreByUuid(uuid);
        if (Objects.isNull(genre)) throw new GenreNotFoundException(uuid);

        return GenreDTO.createFromGenre(genre);
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        var genre = genreDTO.convertDTO();
        genreRepository.save(genre);
        return GenreDTO.createFromGenre(genre);
    }

    @Override
    public GenreDTO updateGenre(String genreUuid, GenreDTO genreDTO) throws GenreNotFoundException {
        var genre = genreRepository.findGenreByUuid(genreUuid);

        if (Objects.isNull(genre)) {
            throw new GenreNotFoundException(genreUuid);
        }

        genre.setName(Objects.isNull(genreDTO.name()) ? genre.getName() : genreDTO.name());

        genreRepository.save(genre);
        return GenreDTO.createFromGenre(genre);
    }

    @Override
    public void deleteGenre(String uuid) {
        genreRepository.deleteGenreByUuid(uuid);
    }
}
