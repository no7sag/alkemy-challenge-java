package com.no7sag.alkemychallengejava.repository;

import com.no7sag.alkemychallengejava.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    ArrayList<Character> findByName(String name);

    ArrayList<Character> findByAge(Integer age);

}