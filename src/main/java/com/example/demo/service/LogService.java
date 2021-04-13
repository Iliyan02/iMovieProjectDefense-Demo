package com.example.demo.service;

import com.example.demo.model.service.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, Long movieId);

    List<LogServiceModel> findAllLogs();
}
