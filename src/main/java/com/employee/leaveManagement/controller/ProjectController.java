package com.employee.leaveManagement.controller;

import com.employee.leaveManagement.dto.project.ProjectRequestDto;
import com.employee.leaveManagement.dto.project.ProjectResponseDto;
import com.employee.leaveManagement.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> addProject(@RequestBody ProjectRequestDto dto) {
        return new ResponseEntity<>(projectService.addProject(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}
