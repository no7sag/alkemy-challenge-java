package com.no7sag.alkemychallengejava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@ApiModel("Entidad Movie (Película)")
@Table(name = "app_movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @ApiModelProperty("Fecha de lanzamiento en formato aaaa/mm/dd (ejemplo: 1970/01/01)")
    private Date releaseDate;

    //Min(value = 1) @Max(value = 5)
    @ApiModelProperty("Número entero del 1 al 5")
    private Integer rating;

    @ManyToMany(mappedBy = "movies")
    @ApiModelProperty("Personajes que pertenecen a la película")
    private Set<Character> characters = new HashSet<>();

    @ManyToOne
    @ApiModelProperty("Género que corresponde a la película")
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Movie() {}

    public Movie(Long id, String image, String title, Date releaseDate, Integer rating, Set<Character> characters, Genre genre) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.characters = characters;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(HashSet<Character> characters) {
        this.characters = characters;
    }

    public Genre getGenres() {
        return genre;
    }

    public void setGenres(Genre genre) {
        this.genre = genre;
    }

}