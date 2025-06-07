package com.armando.academicplatform.dtos.subject;

import com.armando.academicplatform.dtos.course.CourseInfoDTO;
import com.armando.academicplatform.dtos.materials.MaterialInfoDTO;
import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectDetailDTO {
    private String name;
    private CourseInfoDTO course;
    private TeacherInfoDTO teacher;
    private List<MaterialInfoDTO> materials;
}
