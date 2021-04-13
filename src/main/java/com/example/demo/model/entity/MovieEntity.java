package com.example.demo.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {
    @Expose
    @Column(nullable = false)
    private String name;
    @Expose
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Expose
    @Column( name = "background_image", nullable = false)
    private String backgroundImage;
    @Expose
    @Column( name = "video_url", nullable = false)
    private String videoUrl;
    @Expose
    @Column( nullable = false, columnDefinition = "TEXT")
    private String description;
    @Expose
    @Column( name = "release_date")
    private LocalDate releaseDate;
    @Expose
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @Expose
    @Column(name = "rating", nullable = false)
    private double rating;
    @Expose
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Expose
    @Enumerated(EnumType.STRING)
    private Genre genre2;
    @Expose
    @ManyToOne
    private DirectorEntity directorEntity;
    @Expose
    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "movieEntityEntity", cascade = CascadeType.REMOVE)
    private List<LogEntity> logEntities;

    public MovieEntity() {
    }

    public String getName() {
        return name;
    }

    public MovieEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public MovieEntity setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public MovieEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public MovieEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public MovieEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public DirectorEntity getDirectorEntity() {
        return directorEntity;
    }

    public MovieEntity setDirectorEntity(DirectorEntity directorEntity) {
        this.directorEntity = directorEntity;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public MovieEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public MovieEntity setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public Genre getGenre2() {
        return genre2;
    }

    public MovieEntity setGenre2(Genre genre2) {
        this.genre2 = genre2;
        return this;
    }
}
