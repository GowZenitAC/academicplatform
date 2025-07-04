package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    List<Course> findByAcademicYear(String academicYear);
    boolean existsByName(String name);
}
