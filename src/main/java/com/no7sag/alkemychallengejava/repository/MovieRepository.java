package com.no7sag.alkemychallengejava.repository;

import com.no7sag.alkemychallengejava.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    //@Query(value = "SELECT title, image, release_date FROM movies", nativeQuery = true)
    //ArrayList<Movie> findAll(String name);

    ArrayList<Movie> findByTitle(String name);

}