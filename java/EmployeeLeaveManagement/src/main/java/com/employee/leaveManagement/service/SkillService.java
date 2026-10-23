package com.employee.leaveManagement.service;

import com.employee.leaveManagement.dto.Skill.SkillRequestDto;
import com.employee.leaveManagement.dto.Skill.SkillResponseDto;

import java.util.List;

public interface SkillService {
    SkillResponseDto addSkill(SkillRequestDto dto);
    List<SkillResponseDto> getAllSkills();
}
