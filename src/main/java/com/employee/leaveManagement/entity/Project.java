package com.employee.leaveManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String projectName;

    private String clientName;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employee;
}
