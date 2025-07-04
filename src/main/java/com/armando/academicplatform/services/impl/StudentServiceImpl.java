package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.student.StudentRequestDTO;
import com.armando.academicplatform.dtos.student.StudentResponseDTO;
import com.armando.academicplatform.entities.Student;
import com.armando.academicplatform.entities.User;
import com.armando.academicplatform.repositories.StudentRepository;
import com.armando.academicplatform.repositories.UserRepository;
import com.armando.academicplatform.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::mapToStudentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(String codigoMatricula) {
        return studentRepository.findById(codigoMatricula);
    }

    @Override
    @Transactional
    public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
        User user = userRepository.findById(studentRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Student student = new Student();
        student.setCodigoMatricula(studentRequestDTO.getCodigoMatricula());
        student.setUser(user);

        return mapToStudentResponseDTO(studentRepository.save(student));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCodigoMatricula(String codigoMatricula) {
        return studentRepository.existsByCodigoMatricula(codigoMatricula);
    }

    private StudentResponseDTO mapToStudentResponseDTO(Student student){
        return new StudentResponseDTO(
                student.getCodigoMatricula(),
                student.getUser().getName()
        );
    }
}
