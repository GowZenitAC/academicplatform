package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import com.armando.academicplatform.dtos.teacher.TeacherRequestDTO;
import com.armando.academicplatform.dtos.teacher.TeacherSubjectSummaryDTO;
import com.armando.academicplatform.entities.Subject;
import com.armando.academicplatform.entities.Teacher;
import com.armando.academicplatform.entities.User;
import com.armando.academicplatform.repositories.SubjectRepository;
import com.armando.academicplatform.repositories.TeacherRepository;
import com.armando.academicplatform.repositories.UserRepository;
import com.armando.academicplatform.services.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TeacherInfoDTO> findAll() {
        return teacherRepository.findAll().stream()
                .map(this::mapToTeacherInfoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TeacherInfoDTO> findById(Long id) {
        return teacherRepository.findById(id)
                .map(this::mapToTeacherInfoDTO);
    }

    @Override
    public TeacherInfoDTO save(TeacherRequestDTO dto) {
        //buscamos el usuario
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario con el id:" + dto.getUserId()));
        if (teacherRepository.findByUser(user).isPresent())
            throw new IllegalArgumentException("Este usuario ya ha sido asignado");

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setSpeciality(dto.getSpeciality());

        return mapToTeacherInfoDTO(teacherRepository.save(teacher));
    }

    @Override
    public Optional<TeacherInfoDTO> update(Long id, TeacherRequestDTO dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maestro no encontrado con el id: " + id));

        teacher.setSpeciality(dto.getSpeciality());


//         if (!teacher.getUser().getId().equals(dto.getUserId())) {
//             User newUser = userRepository.findById(dto.getUserId())
//                     .orElseThrow(() -> new EntityNotFoundException("New User not found with ID: " + dto.getUserId()));
//             teacher.setUser(newUser);
//         }

        return Optional.of(mapToTeacherInfoDTO(teacherRepository.save(teacher)));
    }

    @Override
    public List<TeacherSubjectSummaryDTO> getTeacherSubjectsDetail(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));

       return teacher.getSubjects().stream()
               .map(subject -> {
                   TeacherSubjectSummaryDTO dto = new TeacherSubjectSummaryDTO();
                   dto.setSubjectId(subject.getId());
                   dto.setSubjectName(subject.getName());

                   if (subject.getCourse() != null) {
                       dto.setCourseId(subject.getCourse().getId());
                       dto.setCourseName(subject.getCourse().getName());
                   }
                   return dto;
               }).collect(Collectors.toList());
    }

    private TeacherInfoDTO mapToTeacherInfoDTO(Teacher teacher){
        TeacherInfoDTO dto = new TeacherInfoDTO();
        dto.setId(teacher.getId());
        dto.setSpeciality(teacher.getSpeciality());

        if (teacher.getUser() != null)
            dto.setName(teacher.getUser().getName());
        return dto;
    }

//    private
}
