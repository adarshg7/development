package com.employee.leaveManagement.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class EmployeeResponseDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String contact;

    private String department;

    private LocalDate joiningDate;

    private List<String> skills;

    private List<String> projects;
}
