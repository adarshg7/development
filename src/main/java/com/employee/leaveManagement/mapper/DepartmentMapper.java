package com.employee.leaveManagement.mapper;

import com.employee.leaveManagement.dto.department.DepartmentRequestDto;
import com.employee.leaveManagement.dto.department.DepartmentResponseDto;
import com.employee.leaveManagement.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity(DepartmentRequestDto dto) {
        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        return department;
    }

    public DepartmentResponseDto toResponse(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        return dto;
    }
}
