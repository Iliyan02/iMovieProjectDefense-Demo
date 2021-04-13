package com.example.demo.model.binding;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

public class MovieAddBindingModel {
    @Size(min = 3, max = 100)
    private String name;
    private String imageUrl;
    private String backgroundImage;
    @Size(min = 0)
    private String videoUrl;
    @Size(min = 20)
    private String description;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Min(0)
    private Integer duration;
    @Min(0)
    private double rating;
    @NotNull
    private Genre genre;
    @NotNull
    private Genre genre2;
    @NotNull
    private String director;

    public String getName() {
        return name;
    }

    public MovieAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public MovieAddBindingModel setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public MovieAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public MovieAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieAddBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public MovieAddBindingModel setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public MovieAddBindingModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Genre getGenre2() {
        return genre2;
    }

    public MovieAddBindingModel setGenre2(Genre genre2) {
        this.genre2 = genre2;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public MovieAddBindingModel setDirector(String director) {
        this.director = director;
        return this;
    }
}
