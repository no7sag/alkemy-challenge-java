package com.no7sag.alkemychallengejava.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ApiModel("Entidad Genre (Género de la película)")
@Table(name = "app_genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "genre")
    @ApiModelProperty("Películas que corresponden al género")
    private Set<Movie> movies = new HashSet<>();

    public Genre() {}

    public Genre(Long id, String name, String image, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(HashSet<Movie> movies) {
        this.movies = movies;
    }

}