package com.no7sag.alkemychallengejava.service;

import com.no7sag.alkemychallengejava.entity.Character;
import com.no7sag.alkemychallengejava.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Optional<Character> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }

    public ArrayList<Character> getCharacterByName(String name) {
        return characterRepository.findByName(name);
    }

    public ArrayList<Character> getCharacterByAge(Integer age) {
        return characterRepository.findByAge(age);
    }

    public boolean deleteCharacter(Long id) {
        if (characterRepository.findById(id).isPresent()) {
            characterRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}