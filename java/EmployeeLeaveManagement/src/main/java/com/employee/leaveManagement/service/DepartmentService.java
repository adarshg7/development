package com.employee.leaveManagement.service;

import com.employee.leaveManagement.dto.department.DepartmentRequestDto;
import com.employee.leaveManagement.dto.department.DepartmentResponseDto;
import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto addDepartment(DepartmentRequestDto dto);
    List<DepartmentResponseDto> getAllDepartments();
}
