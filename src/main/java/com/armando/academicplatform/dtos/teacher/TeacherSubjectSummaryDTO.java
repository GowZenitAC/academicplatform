package com.armando.academicplatform.dtos.teacher;

import com.armando.academicplatform.dtos.course.CourseInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherSubjectSummaryDTO {
    private Long subjectId; //subject id
    private String subjectName; //subject name
    private Long courseId;
    private String courseName;
}
