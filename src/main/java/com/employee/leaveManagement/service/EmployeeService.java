package com.employee.leaveManagement.service;

import com.employee.leaveManagement.dto.employee.EmployeeRequestDto;
import com.employee.leaveManagement.dto.employee.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeRequestDto dto);
    EmployeeResponseDto getEmployeeById(Integer id);
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto dto);
    void deleteEmployee(Integer id);
}
