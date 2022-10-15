package com.no7sag.alkemychallengejava.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ApiModel("Entidad Character (Personaje)")
@Table(name = "app_character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private Integer age;

    @ApiModelProperty("Número decimal en kilogramos")
    private Double weight;

    private String story;

    @ManyToMany
    @ApiModelProperty("Películas que corresponden al personaje")
    @JoinTable(
            name = "character_movie",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<>();

    public Character() {}

    public Character(Long id, String name, String image, Integer age, Double weight, String story, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.weight = weight;
        this.story = story;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(HashSet<Movie> movies) {
        this.movies = movies;
    }

}