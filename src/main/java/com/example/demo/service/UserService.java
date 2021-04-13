package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.UserServiceModel;

public interface UserService {
    boolean userExists(String username);

    void registerAndLogin(UserServiceModel userServiceModel);

    void seedUsers();

    UserEntity findByName(String username);

}
