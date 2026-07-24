package com.employee.leaveManagement.service.impl;

import com.employee.leaveManagement.dto.department.DepartmentRequestDto;
import com.employee.leaveManagement.dto.department.DepartmentResponseDto;
import com.employee.leaveManagement.entity.Department;
import com.employee.leaveManagement.mapper.DepartmentMapper;
import com.employee.leaveManagement.repository.DepartmentRepository;
import com.employee.leaveManagement.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentResponseDto addDepartment(DepartmentRequestDto dto) {
        Department department = departmentMapper.toEntity(dto);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
