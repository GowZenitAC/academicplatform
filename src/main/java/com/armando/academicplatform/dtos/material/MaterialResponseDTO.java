package com.armando.academicplatform.dtos.material;

import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialResponseDTO {
    private String title;

    private String description;

    private String fileUrl;

    private String subjectName;

    private TeacherInfoDTO teacher;

}
