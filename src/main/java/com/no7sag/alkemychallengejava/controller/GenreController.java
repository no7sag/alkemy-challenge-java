package com.no7sag.alkemychallengejava.controller;

import com.no7sag.alkemychallengejava.entity.Character;
import com.no7sag.alkemychallengejava.entity.Genre;
import com.no7sag.alkemychallengejava.service.GenreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping()
    @ApiOperation("Devuelve todos los géneros")
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @PostMapping()
    @ApiOperation("Registra un género")
    public Genre saveGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation("Elimina un género según su clave primaria")
    public String deleteById(@PathVariable("id") Long id) {
        if (genreService.deleteGenre(id))
            return "Género con ID " + id + " eliminado exitosamente";
        else
            return "No se pudo eliminar el género con ID " + id;
    }

}