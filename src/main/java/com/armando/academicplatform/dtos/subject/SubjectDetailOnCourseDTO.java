package com.armando.academicplatform.dtos.subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDetailOnCourseDTO {
    private Long id;
    private String name;
    private String teacherName;
}
