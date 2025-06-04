package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String string);

    boolean existsByUsername(String username);


}
