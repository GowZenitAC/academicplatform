package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
