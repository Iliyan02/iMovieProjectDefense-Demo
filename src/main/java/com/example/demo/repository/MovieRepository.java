package com.example.demo.repository;

import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.view.MovieViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByGenre(Genre genre);

    @Query("SELECT a FROM MovieEntity a where a.genre = :genre OR a.genre2 = :genre")
    List<MovieEntity> findAllByGenre_Name(Genre genre);

    Optional<MovieEntity> findByName(String name);
}
