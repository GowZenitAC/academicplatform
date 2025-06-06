package com.armando.academicplatform.services;

import com.armando.academicplatform.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);

}
