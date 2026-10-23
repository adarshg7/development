package com.employee.leaveManagement.dto.department;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DepartmentResponseDto {
    private Integer id;
    private String departmentName;
}
