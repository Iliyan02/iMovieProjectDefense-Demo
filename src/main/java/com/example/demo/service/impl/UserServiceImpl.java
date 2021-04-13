package com.example.demo.service.impl;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.UserRoleEntity;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MovieDBUserService movieDBUserService;
    private final UserRoleRepository userRoleRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, MovieDBUserService movieDBUserService, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.movieDBUserService = movieDBUserService;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public boolean userExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void registerAndLogin(UserServiceModel userServiceModel) {
        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER).orElseThrow(
                () -> new IllegalStateException("USER role not found. Please seed the roles.")
        );

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = movieDBUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0){
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity().setUsername("admin").setPassword(passwordEncoder.encode("topsecret"));
            UserEntity user = new UserEntity().setUsername("user").setPassword(passwordEncoder.encode("topsecret"));

            admin.setRoles(List.of(adminRole, userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));

        }
    }

    @Override
    public UserEntity findByName(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }
}
