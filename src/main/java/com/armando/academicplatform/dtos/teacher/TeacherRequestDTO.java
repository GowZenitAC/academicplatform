package com.armando.academicplatform.dtos.teacher;

import com.armando.academicplatform.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDTO {
    private Long userId;
    private String speciality;
}
