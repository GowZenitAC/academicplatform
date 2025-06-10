package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.material.MaterialBySubjectDTO;
import com.armando.academicplatform.dtos.material.MaterialRequestDTO;
import com.armando.academicplatform.dtos.material.MaterialResponseDTO;
import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import com.armando.academicplatform.entities.Material;
import com.armando.academicplatform.entities.Subject;
import com.armando.academicplatform.entities.Teacher;
import com.armando.academicplatform.repositories.MaterialRepository;
import com.armando.academicplatform.repositories.SubjectRepository;
import com.armando.academicplatform.repositories.TeacherRepository;
import com.armando.academicplatform.services.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public MaterialResponseDTO save(MaterialRequestDTO dto) {
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra la asignatura con el id: " + dto.getSubjectId()));
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra el maestro con el id: " + dto.getTeacherId()));

        Material material = new Material();
        material.setTitle(dto.getTitle());
        material.setDescription(dto.getDescription());
        material.setFile_url(dto.getFileUrl());
        material.setSubject(subject);
        material.setTeacher(teacher);

        return mapToMaterialResponse(materialRepository.save(material));
    }

    @Override
    public Optional<MaterialResponseDTO> delete(Long id) {
        var material = materialRepository.findById(id).orElseThrow();
        materialRepository.deleteById(id);
        return Optional.of(mapToMaterialResponse(material));
    }

    @Override
    public List<MaterialBySubjectDTO> findBySubjects(Long subjectId) {
        var subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra la materia"));
        var materials = materialRepository.findBySubject(subject);

       return materials.stream()
               .map(this::mapToMaterialBySubject)
               .collect(Collectors.toList());
    }

    private MaterialResponseDTO mapToMaterialResponse(Material material){
        MaterialResponseDTO dto = new MaterialResponseDTO();
        dto.setTitle(material.getTitle());
        dto.setDescription(material.getDescription());
        dto.setFileUrl(material.getFile_url());
        dto.setTeacher(mapToTeacherInfoDTO(material.getTeacher()));
        dto.setSubjectName(material.getSubject().getName());
        return dto;
    }

    private TeacherInfoDTO mapToTeacherInfoDTO(Teacher teacher){
        TeacherInfoDTO dto = new TeacherInfoDTO();
        dto.setId(teacher.getId());
        dto.setSpeciality(teacher.getSpeciality());

        if (teacher.getUser() != null)
            dto.setName(teacher.getUser().getName());
        return dto;
    }

    private MaterialBySubjectDTO mapToMaterialBySubject(Material material){
        MaterialBySubjectDTO dto = new MaterialBySubjectDTO();
        dto.setTitle(material.getTitle());
        dto.setDescription(material.getTitle());
        dto.setFileUrl(material.getFile_url());
//        dto.setSubjectName(material.getSubject().getName());
        dto.setTeacher(mapToTeacherInfoDTO(material.getTeacher()));
        return dto;
    }
}
