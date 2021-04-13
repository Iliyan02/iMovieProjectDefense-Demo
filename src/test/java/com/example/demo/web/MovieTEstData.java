package com.example.demo.web;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.DirectorsRepository;
import com.example.demo.repository.LogRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;

import java.time.LocalDate;

public class MovieTEstData {

    private long testMovieId;

    private UserRepository userRepository;
    private DirectorsRepository directorsRepository;
    private MovieRepository movieRepository;
    private LogRepository logRepository;

    public MovieTEstData(UserRepository userRepository,
                         DirectorsRepository directorsRepository, MovieRepository movieRepository,
                         LogRepository logRepository) {
        this.userRepository = userRepository;
        this.directorsRepository = directorsRepository;
        this.movieRepository = movieRepository;
        this.logRepository = logRepository;
    }

    public void init(){
        DirectorEntity directorEntity = new DirectorEntity();
        directorEntity.setName("steven Spielberg");
        directorEntity.setCareerInformation("Some info about Steven");
        directorEntity = directorsRepository.save(directorEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Pesho").setPassword("xyz");
        userEntity = userRepository.save(userEntity);

        MovieEntity movieAvengers = new MovieEntity();
        movieAvengers.
                setName("Avengers: Infinity War").
                setImageUrl("https://melange.bg/wp-content/uploads/2018/05/poster.jpg").
                setBackgroundImage("https://i.ytimg.com/vi/QwievZ1Tx-8/maxresdefault.jpg")
                .setDescription("The Avengers must stop Thanos, an intergalactic warlord, from getting his hands on all the infinity stones. However, Thanos is prepared to go to any lengths to carry out his insane plan.")
                .setDuration(160)
                .setGenre(Genre.ACTION).setGenre2(Genre.FANTASY)
                .setReleaseDate(LocalDate.now())
                .setDirectorEntity(directorEntity)
                .setUserEntity(userEntity)
                .setVideoUrl("6ZfuNTqbHE8").setRating(8);

        movieAvengers = movieRepository.save(movieAvengers);

        MovieEntity movieTheTransporter = new MovieEntity();
        movieTheTransporter
                .setName("The Transporter")
                .setBackgroundImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTP4fM5ITyphlyKpCuH3hn_KmjkPVE1nlYK1Q&usqp=CAU")
                .setDescription("An ex-soldier turned mercenary 'transporter' moves goods, human or otherwise, from one place to another. Complications arise when a job goes astray and he has to save the life of his female cargo.")
                .setDuration(93)
                .setGenre(Genre.ACTION)
                .setGenre2(Genre.THRILLER)
                .setImageUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQGmbA31yu_iLejZJjDB79cAqgfcIuJyfYHOGm570-feIK3KBBl")
                .setRating(7)
                .setReleaseDate(LocalDate.now())
                .setVideoUrl("0poXFSvX0_4")
                .setUserEntity(userEntity)
                .setDirectorEntity(directorEntity);

        movieTheTransporter = movieRepository.save(movieTheTransporter);

        testMovieId = movieAvengers.getId();
    }

    void cleanUp(){
        logRepository.deleteAll();
        movieRepository.deleteAll();
        directorsRepository.deleteAll();
        userRepository.deleteAll();
    }

    public long getTestMovieId(){
        return testMovieId;
    }

}
