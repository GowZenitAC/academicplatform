package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.course.CourseRequestDTO;
import com.armando.academicplatform.dtos.course.CourseResponseDTO;
import com.armando.academicplatform.entities.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseResponseDTO> findAll();

    Optional<CourseResponseDTO> findById(Long id);

    CourseResponseDTO create(CourseRequestDTO courseRequestDTO);

    Optional<CourseResponseDTO> update(Long id, CourseRequestDTO courseRequestDTO);

    void delete(Long id);
}
