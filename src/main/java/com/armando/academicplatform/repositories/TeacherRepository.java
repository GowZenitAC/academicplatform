package com.armando.academicplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.armando.academicplatform.entities.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findBySpeciality(String speciality);

}
