package com.no7sag.alkemychallengejava.controller;

import com.no7sag.alkemychallengejava.entity.Movie;
import com.no7sag.alkemychallengejava.service.MovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    @ApiOperation("Devuelve todas las películas")
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping()
    @ApiOperation("Registra una película")
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping(params = "name")
    @ApiOperation("Busca una película según su nombre (título)")
    public ArrayList<Movie> getMovieByName(@RequestParam("name") String name) {
        return movieService.getMovieByTitle(name);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation("Elimina una películas según su clave primaria")
    public String deleteById(@PathVariable("id") Long id) {
        if (movieService.deleteMovie(id))
            return "Película con ID " + id + " eliminado exitosamente";
        else
            return "No se pudo eliminar la película con ID " + id;
    }

}