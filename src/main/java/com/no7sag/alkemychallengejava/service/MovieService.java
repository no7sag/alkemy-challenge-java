package com.no7sag.alkemychallengejava.service;

import com.no7sag.alkemychallengejava.entity.Movie;
import com.no7sag.alkemychallengejava.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public ArrayList<Movie> getMovieByTitle(String name) {
        return movieRepository.findByTitle(name);
    }

    public boolean deleteMovie(Long id) {
        if (movieRepository.findById(id).isPresent()) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}