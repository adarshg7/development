package com.employee.leaveManagement.mapper;

import com.employee.leaveManagement.dto.employee.EmployeeRequestDto;
import com.employee.leaveManagement.dto.employee.EmployeeResponseDto;
import com.employee.leaveManagement.entity.Employee;
import com.employee.leaveManagement.entity.Skill;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequestDto dto){
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setContact(dto.getContact());
        employee.setJoiningDate(dto.getJoiningDate());

        return employee;
    }

    public EmployeeResponseDto toResponse(Employee employee){
        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setContact(employee.getContact());
        dto.setJoiningDate(employee.getJoiningDate());


        if(employee.getDepartment() != null){
            dto.setDepartment(
                    employee.getDepartment().getDepartmentName()
            );
        }

        if(employee.getSkills() != null){
            dto.setSkills(
                    employee.getSkills()
                            .stream()
                            .map(Skill::getSkillName)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}

