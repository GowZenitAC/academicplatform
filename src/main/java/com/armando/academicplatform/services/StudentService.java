package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.student.StudentRequestDTO;
import com.armando.academicplatform.dtos.student.StudentResponseDTO;
import com.armando.academicplatform.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponseDTO> findAll();

    Optional<Student> findById(String codigoMatricula);

    StudentResponseDTO save(StudentRequestDTO studentRequestDTO);


    boolean existsByCodigoMatricula(String codigoMatricula);
}
