package com.example.demo.model.service;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.LocalDate;

public class MovieServiceModel {

    private String name;
    private String imageUrl;
    private String backgroundImage;
    private String videoUrl;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Instant releaseDate;
    private Integer duration;
    private double rating;
    private Genre genre;
    private Genre genre2;
    private String director;
    private String user;


    public String getName() {
        return name;
    }

    public MovieServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public MovieServiceModel setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public MovieServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public MovieServiceModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieServiceModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public MovieServiceModel setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public MovieServiceModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Genre getGenre2() {
        return genre2;
    }

    public MovieServiceModel setGenre2(Genre genre2) {
        this.genre2 = genre2;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public MovieServiceModel setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getUser() {
        return user;
    }

    public MovieServiceModel setUser(String user) {
        this.user = user;
        return this;
    }
}
