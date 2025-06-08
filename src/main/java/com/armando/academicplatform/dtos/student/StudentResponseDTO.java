package com.armando.academicplatform.dtos.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO {
    private String codigoMatricula;
    private String fullName;

    public StudentResponseDTO(String codigoMatricula, String fullName) {
        this.codigoMatricula = codigoMatricula;
        this.fullName = fullName;
    }

}
