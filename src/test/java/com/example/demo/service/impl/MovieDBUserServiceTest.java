package com.example.demo.service.impl;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.UserRoleEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieDBUserServiceTest {

    private MovieDBUserService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp(){
        serviceToTest = new MovieDBUserService(mockUserRepository);
    }

    @Test
    void testUserNotFound(){
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceToTest.loadUserByUsername("user_does_not_exits");
                }
        );
    }

    @Test
    void testExistingUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Iliyan");
        userEntity.setPassword("xyz");

        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRole.USER);
        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(UserRole.ADMIN);

        userEntity.setRoles(List.of(roleUser, roleAdmin));

        Mockito.when(mockUserRepository.findByUsername("Iliyan")).
                thenReturn(Optional.of(userEntity));

        UserDetails userDetails = serviceToTest.loadUserByUsername("Iliyan");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        List<String> authorities = userDetails.
                getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }
}