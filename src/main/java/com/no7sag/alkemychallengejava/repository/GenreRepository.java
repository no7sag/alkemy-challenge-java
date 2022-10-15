package com.no7sag.alkemychallengejava.repository;

import com.no7sag.alkemychallengejava.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}