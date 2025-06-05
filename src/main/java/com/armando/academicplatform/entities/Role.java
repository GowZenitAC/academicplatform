package com.armando.academicplatform.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private RoleName name;

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_TEACHER,
        ROLE_STUDENT
    }
}
