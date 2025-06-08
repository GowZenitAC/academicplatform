package com.armando.academicplatform.dtos.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO {
    private String codigoMatricula;
    private String fullName;
    private String username;

    public StudentResponseDTO(String codigoMatricula, String fullName, String username) {
        this.codigoMatricula = codigoMatricula;
        this.fullName = fullName;
        this.username = username;
    }

}
