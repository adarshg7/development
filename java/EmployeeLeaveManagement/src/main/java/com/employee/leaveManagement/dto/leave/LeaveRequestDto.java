package com.employee.leaveManagement.dto.leave;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {
    private Integer employeeId;
    private Integer leaveTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
