package com.example.demo.model.view;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.Instant;


public class MovieViewModel {

    private long id;
    private String name;
    private String imageUrl;
    private String backgroundImage;
    private String videoUrl;
    private String description;
    private Instant releaseDate;
    private Integer duration;
    private double rating;
    private Genre genre;
    private Genre genre2;
    private String director;

    public String getName() {
        return name;
    }

    public MovieViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public MovieViewModel setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public MovieViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Instant getReleaseDate() {
        return releaseDate;
    }

    public MovieViewModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieViewModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public MovieViewModel setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public MovieViewModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Genre getGenre2() {
        return genre2;
    }

    public MovieViewModel setGenre2(Genre genre2) {
        this.genre2 = genre2;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public MovieViewModel setDirector(String directorEntity) {
        this.director = directorEntity;
        return this;
    }

    public long getId() {
        return id;
    }

    public MovieViewModel setId(long id) {
        this.id = id;
        return this;
    }
}
