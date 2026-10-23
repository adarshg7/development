package com.employee.leaveManagement.dto.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponseDto {
    private Integer id;
    private String projectName;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
}
