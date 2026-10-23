package com.employee.leaveManagement.dto.leaveType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LeaveTypeResponseDto {

    private Integer id;

    private String leaveName;

    private Integer maximumLeaves;
}