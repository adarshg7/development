package com.employee.leaveManagement.mapper;

import com.employee.leaveManagement.dto.Skill.SkillRequestDto;
import com.employee.leaveManagement.dto.Skill.SkillResponseDto;
import com.employee.leaveManagement.entity.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {
    public Skill toEntity(SkillRequestDto dto){
        Skill skill = new Skill();
        skill.setSkillName(dto.getSkillName());
        return skill;
    }

    public SkillResponseDto toResponse(Skill skill){
        SkillResponseDto dto = new SkillResponseDto();
        dto.setId(skill.getId());
        dto.setSkillName(skill.getSkillName());
        return dto;
    }
}
