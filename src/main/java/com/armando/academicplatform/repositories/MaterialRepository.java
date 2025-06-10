package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Material;
import com.armando.academicplatform.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findBySubject(Subject subject);
}
