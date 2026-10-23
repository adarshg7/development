package com.employee.leaveManagement.dto.leaveType;

import lombok.Data;

@Data
public class LeaveTypeRequestDto {

    private String leaveName;

    private Integer maximumLeaves;
}