package com.armando.academicplatform.dtos.course;

import com.armando.academicplatform.dtos.subject.SubjectResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseDTO {
    private Long id;
    private String name;
    private String academicYear;
    private List<SubjectResponseDTO> subjects;
}
