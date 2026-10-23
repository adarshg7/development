package com.employee.leaveManagement.mapper;

import com.employee.leaveManagement.dto.project.ProjectRequestDto;
import com.employee.leaveManagement.dto.project.ProjectResponseDto;
import com.employee.leaveManagement.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequestDto dto) {
        Project project = new Project();
        project.setProjectName(dto.getProjectName());
        project.setClientName(dto.getClientName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        return project;
    }

    public ProjectResponseDto toResponse(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setProjectName(project.getProjectName());
        dto.setClientName(project.getClientName());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        return dto;
    }
}
