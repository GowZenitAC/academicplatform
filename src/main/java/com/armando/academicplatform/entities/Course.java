package com.armando.academicplatform.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 4)
    private String academicYear;

    @OneToMany(mappedBy = "course")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "course")
    private List<Matricula> matriculas;






}
