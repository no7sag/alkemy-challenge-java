package com.no7sag.alkemychallengejava.controller;

import com.no7sag.alkemychallengejava.entity.Character;
import com.no7sag.alkemychallengejava.service.CharacterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping()
    @ApiOperation("getCharacters: Devuelve todos los personajes")
    public List<Character> getCharacters() {
        return characterService.getCharacters();
    }

    @PostMapping()
    @ApiOperation("saveCharacter: Registra un personaje")
    public Character saveCharacter(@RequestBody Character character) {
        return characterService.saveCharacter(character);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("getCharacterById: Elimina un personaje según su clave primaria")
    public Optional<Character> getCharacterById(@PathVariable("id") Long id) {
        return characterService.getCharacterById(id);
    }

    @GetMapping(params = "name")
    @ApiOperation("getCharacterByName: Busca un personaje según su nombre")
    public ArrayList<Character> getCharacterByName(@RequestParam("name") String name) {
        return characterService.getCharacterByName(name);
    }

    @GetMapping(params = "age")
    @ApiOperation("getCharacterByAge: Busca un personaje según su edad")
    public ArrayList<Character> getCharacterByAge(@RequestParam("age") Integer age) {
        return characterService.getCharacterByAge(age);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation("deleteById: Borra un personaje según su clave primaria")
    public String deleteById(@PathVariable("id") Long id) {
        if (characterService.deleteCharacter(id))
            return "Personaje con ID " + id + " eliminado exitosamente";
        else
            return "No se pudo eliminar el personaje con ID " + id;
    }

}