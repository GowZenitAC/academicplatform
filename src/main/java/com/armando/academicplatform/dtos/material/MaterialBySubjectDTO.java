package com.armando.academicplatform.dtos.material;

import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialBySubjectDTO {

    private TeacherInfoDTO teacher;

    private String title;

    private String description;

    private String fileUrl;
}
