package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRoleEntity setRole(UserRole role){
        this.role = role;
        return this;
    }
}

