package com.spring.vidly.controller;

import com.spring.vidly.dto.MovieDTO;
import com.spring.vidly.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;

    @GetMapping("/")
    public List<MovieDTO> getAll() {
        return movieService.getAll();
    }

    @PostMapping("/")
    public MovieDTO createMovie(@RequestBody MovieDTO movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/{uuid}")
    public MovieDTO getMovie(@PathVariable(name = "uuid") String uuid) {
        return movieService.getMovie(uuid);
    }

    @PutMapping("/{uuid}")
    public MovieDTO updateMovie(@PathVariable String uuid,@RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(uuid, movieDTO);
    }

    @DeleteMapping("/{uuid}")
    public void deleteMovie(@PathVariable String uuid) {
         movieService.deleteMovie(uuid);
    }
}
