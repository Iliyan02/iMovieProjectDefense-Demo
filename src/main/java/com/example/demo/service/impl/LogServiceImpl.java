package com.example.demo.service.impl;

import com.example.demo.model.entity.LogEntity;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.LogServiceModel;
import com.example.demo.repository.LogRepository;
import com.example.demo.service.LogService;
import com.example.demo.service.MovieService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    private final MovieService movieService;
    private final UserService userService;
    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    public LogServiceImpl(MovieService movieService, UserService userService, LogRepository logRepository, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.userService = userService;
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createLog(String action, Long movieId) {
        MovieEntity movieEntity = movieService
                .findEntityById(movieId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();

        UserEntity userEntity = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setMovieEntityEntity(movieEntity)
                .setUserEntity(userEntity)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel = modelMapper
                            .map(logEntity, LogServiceModel.class);
                    logServiceModel.setMovie(logEntity.getMovieEntityEntity().getName())
                            .setUser(logEntity.getUserEntity().getUsername());

                    return logServiceModel;
                }).collect(Collectors.toList());
    }
}
