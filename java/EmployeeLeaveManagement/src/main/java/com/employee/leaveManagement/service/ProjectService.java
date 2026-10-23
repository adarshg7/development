package com.employee.leaveManagement.service;

import com.employee.leaveManagement.dto.project.ProjectRequestDto;
import com.employee.leaveManagement.dto.project.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto addProject(ProjectRequestDto dto);
    List<ProjectResponseDto> getAllProjects();
}
