package com.spring.vidly.controller;

import com.spring.vidly.domain.Genre;
import com.spring.vidly.dto.GenreDTO;
import com.spring.vidly.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;

    @GetMapping("/")
    public List<GenreDTO> getAll() {
        return genreService.getAll();
    }

    @PostMapping("/")
    public GenreDTO createGenre(@RequestBody GenreDTO genre) {
        return genreService.createGenre(genre);
    }

    @GetMapping("/{uuid}")
    public GenreDTO getGenre(@PathVariable(name = "uuid") String uuid) {
        return genreService.getGenre(uuid);
    }

    @PutMapping("/{uuid}")
    public GenreDTO updateGenre(@PathVariable String uuid, @RequestBody GenreDTO genreDTO) {
        return genreService.updateGenre(uuid, genreDTO);
    }

    @DeleteMapping("/{uuid}")
    public void deleteGenre(@PathVariable String uuid) {
        genreService.deleteGenre(uuid);
    }
}
