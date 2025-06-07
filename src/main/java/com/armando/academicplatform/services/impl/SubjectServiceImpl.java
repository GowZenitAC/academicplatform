package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.course.CourseInfoDTO;
import com.armando.academicplatform.dtos.materials.MaterialInfoDTO;
import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import com.armando.academicplatform.dtos.subject.SubjectRequestDTO;
import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import com.armando.academicplatform.entities.Course;
import com.armando.academicplatform.entities.Material;
import com.armando.academicplatform.entities.Subject;
import com.armando.academicplatform.entities.Teacher;
import com.armando.academicplatform.repositories.CourseRepository;
import com.armando.academicplatform.repositories.SubjectRepository;
import com.armando.academicplatform.repositories.TeacherRepository;
import com.armando.academicplatform.services.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<SubjectDetailDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(this::mapToSubjectDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SubjectDetailDTO> findById(Long id) {
        return subjectRepository.findById(id)
                .map(this::mapToSubjectDetailDTO);
    }

    @Override
    public SubjectDetailDTO save(SubjectRequestDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el curso con el id: " + dto.getCourseId()));

        Teacher teacher = null;
        if (dto.getProfessorId() != null) {
            teacher = teacherRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el maestro con el id: " + dto.getProfessorId()));
        }

        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setTeacher(teacher);
        subject.setCourse(course);


        return mapToSubjectDetailDTO(subjectRepository.save(subject));
    }

    @Override
    public Optional<SubjectDetailDTO> update(Long id, SubjectRequestDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la asignatura con el id: " + id));

        subject.setName(dto.getName());
        if (!subject.getCourse().getId().equals(dto.getCourseId())){
            Course newCourse = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el curso con el id: " + dto.getCourseId()));
            subject.setCourse(newCourse);
        }

        if (subject.getTeacher() == null || !subject.getTeacher().getId().equals(dto.getProfessorId())){
            Teacher newTeacher = teacherRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el maestro con el id: " + dto.getProfessorId()));
            subject.setTeacher(newTeacher);
        }

        return Optional.of(mapToSubjectDetailDTO(subjectRepository.save(subject)));
    }

    @Override
    public Optional<Subject> delete(Long id) {
        var optionalSubject = subjectRepository.findById(id);
        optionalSubject.ifPresent(subjectRepository::delete);
        return optionalSubject;

    }

    private SubjectDetailDTO mapToSubjectDetailDTO(Subject subject){
        SubjectDetailDTO dto = new SubjectDetailDTO();
        dto.setName(subject.getName());


        if (subject.getCourse() != null)
            dto.setCourse(mapToCourseInfoDTO(subject.getCourse()));

        if (subject.getTeacher() != null)
            dto.setTeacher(mapToTeacherInfoDTO(subject.getTeacher()));

        if (subject.getMaterials() != null)
            dto.setMaterials(mapToMaterialInfoDTO(subject.getMaterials()));
        else
            dto.setMaterials(List.of());

        return dto;
    }

    private CourseInfoDTO mapToCourseInfoDTO(Course course){
        CourseInfoDTO dto = new CourseInfoDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setAcademicYear(course.getAcademicYear());
        return dto;
    }

    private TeacherInfoDTO mapToTeacherInfoDTO(Teacher teacher) {
        TeacherInfoDTO dto = new TeacherInfoDTO();
        dto.setId(teacher.getId());
        dto.setSpeciality(teacher.getSpeciality());
        dto.setName(teacher.getUser().getName());
        return dto;
    }

    private List<MaterialInfoDTO> mapToMaterialInfoDTO(List<Material> materials){
        if (materials == null)
            return List.of();
        return materials.stream()
                .map( mt -> {
                    MaterialInfoDTO dto = new MaterialInfoDTO();
                    dto.setId(mt.getId());
                    dto.setTitle(mt.getTitle());
                    dto.setDescription(mt.getDescription());
                    dto.setFile_url(mt.getFile_url());
                    return dto;
                }).collect(Collectors.toList());
    }
}
