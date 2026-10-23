package com.employee.leaveManagement.controller;

import com.employee.leaveManagement.dto.employee.EmployeeRequestDto;
import com.employee.leaveManagement.dto.employee.EmployeeResponseDto;
import com.employee.leaveManagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponseDto addEmployee(@RequestBody EmployeeRequestDto dto){
        return employeeService.addEmployee(dto);
    }

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(
            @PathVariable Integer id,
            @RequestBody EmployeeRequestDto dto
    ){
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }

}
