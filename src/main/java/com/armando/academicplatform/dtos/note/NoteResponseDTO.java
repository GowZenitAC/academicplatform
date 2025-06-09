package com.armando.academicplatform.dtos.note;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.dtos.student.StudentResponseDTO;
import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoteResponseDTO {
    private String studentName;

    private String subjectName;

    private PeriodDTO period;

    private Double value;

    private String observations;
}
