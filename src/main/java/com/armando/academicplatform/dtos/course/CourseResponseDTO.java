package com.armando.academicplatform.dtos.course;

import com.armando.academicplatform.dtos.subject.SubjectDetailOnCourseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseDTO {
    private String name;
    private String academicYear;
    private List<SubjectDetailOnCourseDTO> subjects;
}
