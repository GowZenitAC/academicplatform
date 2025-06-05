package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByCodigoMatricula(String codigo_matricula);
}
