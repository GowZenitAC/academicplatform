package com.armando.academicplatform.dtos.subject;

import com.armando.academicplatform.dtos.course.CourseInfoDTO;
import com.armando.academicplatform.dtos.materials.MaterialInfoDTO;
import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;

import java.util.List;

public class SubjectDetailDTO {
    private Long id;
    private String name;
    private CourseInfoDTO course;
    private TeacherInfoDTO teacher;
    private List<MaterialInfoDTO> materials;
}
