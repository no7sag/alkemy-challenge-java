package com.no7sag.alkemychallengejava.service;

import com.no7sag.alkemychallengejava.entity.Genre;
import com.no7sag.alkemychallengejava.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public boolean deleteGenre(Long id) {
        if (genreRepository.findById(id).isPresent()) {
            genreRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}