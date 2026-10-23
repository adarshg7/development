package com.employee.leaveManagement.dto.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequestDto {
    private String projectName;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
}
