package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Role;
import com.armando.academicplatform.entities.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
