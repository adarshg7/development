package com.employee.leaveManagement.service.impl;

import com.employee.leaveManagement.dto.project.ProjectRequestDto;
import com.employee.leaveManagement.dto.project.ProjectResponseDto;
import com.employee.leaveManagement.entity.Project;
import com.employee.leaveManagement.mapper.ProjectMapper;
import com.employee.leaveManagement.repository.ProjectRepository;
import com.employee.leaveManagement.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto dto) {
        Project project = projectMapper.toEntity(dto);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponse(savedProject);
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }
}
