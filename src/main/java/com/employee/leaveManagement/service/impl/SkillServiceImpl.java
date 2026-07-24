package com.employee.leaveManagement.service.impl;

import com.employee.leaveManagement.dto.Skill.SkillRequestDto;
import com.employee.leaveManagement.dto.Skill.SkillResponseDto;
import com.employee.leaveManagement.entity.Skill;
import com.employee.leaveManagement.mapper.SkillMapper;
import com.employee.leaveManagement.repository.SkillRepository;
import com.employee.leaveManagement.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillResponseDto addSkill(SkillRequestDto dto) {
        Skill skill = skillMapper.toEntity(dto);
        Skill savedSkill = skillRepository.save(skill);
        return skillMapper.toResponse(savedSkill);
    }

    @Override
    public List<SkillResponseDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillMapper::toResponse)
                .collect(Collectors.toList());
    }
}
