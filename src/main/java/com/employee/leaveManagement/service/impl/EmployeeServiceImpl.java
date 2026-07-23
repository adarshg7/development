package com.employee.leaveManagement.service.impl;

import com.employee.leaveManagement.dto.employee.EmployeeRequestDto;
import com.employee.leaveManagement.dto.employee.EmployeeResponseDto;
import com.employee.leaveManagement.entity.Department;
import com.employee.leaveManagement.entity.Employee;
import com.employee.leaveManagement.entity.Skill;
import com.employee.leaveManagement.mapper.EmployeeMapper;
import com.employee.leaveManagement.repository.DepartmentRepository;
import com.employee.leaveManagement.repository.EmployeeRepository;
import com.employee.leaveManagement.repository.ProjectRepository;
import com.employee.leaveManagement.repository.SkillRepository;
import com.employee.leaveManagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final SkillRepository skillRepository;

    private final ProjectRepository projectRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, SkillRepository skillRepository, ProjectRepository projectRepository, EmployeeMapper employeeMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.employeeMapper = employeeMapper;
    }



    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto){
        Employee employee = employeeMapper.toEntity(dto);

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department Not Found"));

        employee.setDepartment(department);

        List<Skill> skills = skillRepository.getAllById(dto.getSkillIds());
        employee.setSkills(skills);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("employee not found"));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("employee not found"));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setContact(dto.getContact());
        employee.setJoiningDate(dto.getJoiningDate());

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);
        employee.setSkills(skillRepository.findAllById(dto.getSkillIds()));
        employee.setProject(projectRepository.findAllById(dto.getProjectIds()));
        Employee updateEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(updateEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("employee nopt found"));

        employeeRepository.delete(employee);
    }


}
