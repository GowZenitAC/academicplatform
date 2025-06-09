package com.armando.academicplatform.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String userName;

    private String fullName;

    private String role;

    private boolean enabled;
}
