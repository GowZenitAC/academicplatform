package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.course.CourseRequestDTO;
import com.armando.academicplatform.dtos.course.CourseResponseDTO;
import com.armando.academicplatform.dtos.subject.SubjectDetailOnCourseDTO;
import com.armando.academicplatform.entities.Course;
import com.armando.academicplatform.entities.Subject;
import com.armando.academicplatform.repositories.CourseRepository;
import com.armando.academicplatform.repositories.SubjectRepository;
import com.armando.academicplatform.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<CourseResponseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(this::mapperToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseResponseDTO> findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Curso no encontrado"));
        return Optional.of(mapperToResponse(course));
    }

    @Override
    public CourseResponseDTO create(CourseRequestDTO courseRequestDTO) {
        if (courseRepository.existsByName(courseRequestDTO.getName()))
            throw new RuntimeException("Ya existe un curso con ese nombre");

        Course newCourse = new Course();
        newCourse.setName(courseRequestDTO.getName());
        newCourse.setAcademicYear(courseRequestDTO.getAcademicYear());

        return mapperToResponse(courseRepository.save(newCourse));
    }

    @Override
    public Optional<CourseResponseDTO> update(Long id, CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (!course.getName().equals(courseRequestDTO.getName()) && courseRepository.existsByName(courseRequestDTO.getName())) {
            throw new RuntimeException("Ya existe un curso con ese nombre");
        }

        course.setName(courseRequestDTO.getName());
        course.setAcademicYear(course.getAcademicYear());
        return Optional.of(mapperToResponse(courseRepository.save(course)));
    }

    @Override
    public void delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (!course.getSubjects().isEmpty()) {
            throw new RuntimeException("No se puede eliminar un curso con asignaturas asociadas");
        }

        courseRepository.delete(course);
    }

    private CourseResponseDTO mapperToResponse(Course course){
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setAcademicYear(course.getAcademicYear());

        List<SubjectDetailOnCourseDTO> subjectResponsDTOS = course.getSubjects().stream()
                .map(this::mapperToSubjectResponseDto)
                .collect(Collectors.toList());
        dto.setSubjects(subjectResponsDTOS);
        return dto;
    }

    private SubjectDetailOnCourseDTO mapperToSubjectResponseDto(Subject subject){
        SubjectDetailOnCourseDTO dto = new SubjectDetailOnCourseDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        if (subject.getTeacher() != null && subject.getTeacher().getUser() != null) {
            dto.setTeacherName(subject.getTeacher().getUser().getName());
        }
        return dto;
    }
}
