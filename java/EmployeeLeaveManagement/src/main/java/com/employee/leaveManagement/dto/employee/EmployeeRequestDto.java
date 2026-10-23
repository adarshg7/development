package com.employee.leaveManagement.dto.employee;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class EmployeeRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String contact;

    private LocalDate joiningDate;

    private List<Integer> skillIds;

    private List<Integer> projectIds;

    private Integer departmentId;

}
