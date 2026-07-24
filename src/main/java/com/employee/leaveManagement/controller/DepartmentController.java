package com.employee.leaveManagement.controller;

import com.employee.leaveManagement.dto.department.DepartmentRequestDto;
import com.employee.leaveManagement.dto.department.DepartmentResponseDto;
import com.employee.leaveManagement.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> addDepartment(@RequestBody DepartmentRequestDto dto) {
        return new ResponseEntity<>(departmentService.addDepartment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
