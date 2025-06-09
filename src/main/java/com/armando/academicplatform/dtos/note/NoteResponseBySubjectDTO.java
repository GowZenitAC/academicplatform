package com.armando.academicplatform.dtos.note;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.dtos.student.StudentResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoteResponseBySubjectDTO {

    private String subjectName;

    private StudentResponseDTO student;

    private PeriodDTO period;

    private Double value;

    private String observations;
}
