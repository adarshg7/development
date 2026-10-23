package com.employee.leaveManagement.controller;

import com.employee.leaveManagement.dto.Skill.SkillRequestDto;
import com.employee.leaveManagement.dto.Skill.SkillResponseDto;
import com.employee.leaveManagement.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillResponseDto> addSkill(@RequestBody SkillRequestDto dto) {
        return new ResponseEntity<>(skillService.addSkill(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}
