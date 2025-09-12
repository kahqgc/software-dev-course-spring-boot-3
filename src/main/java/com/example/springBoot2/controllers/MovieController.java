package com.example.springBoot2.controllers;
import com.example.springBoot2.models.Movie;
import com.example.springBoot2.Repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("")
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id){
        return movieRepository.findById(id).orElse(null);
    }

    @PostMapping("")
    public Movie addMovie (@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Optional<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movie){
        return movieRepository.findById(id).map(existingMovie -> {
            existingMovie.setName(movie.getName());
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setYear(movie.getYear());
            existingMovie.setRuntime(movie.getRuntime());
            return movieRepository.save(existingMovie);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){
        movieRepository.deleteById(id);
    }
}
