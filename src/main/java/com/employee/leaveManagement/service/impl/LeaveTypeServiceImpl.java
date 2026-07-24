package com.employee.leaveManagement.service.impl;

import com.employee.leaveManagement.dto.leaveType.LeaveTypeRequestDto;
import com.employee.leaveManagement.dto.leaveType.LeaveTypeResponseDto;
import com.employee.leaveManagement.entity.LeaveType;
import com.employee.leaveManagement.mapper.LeaveTypeMapper;
import com.employee.leaveManagement.repository.LeaveTypeRepository;
import com.employee.leaveManagement.service.LeaveTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveTypeMapper leaveTypeMapper;

    public LeaveTypeServiceImpl(LeaveTypeRepository leaveTypeRepository, LeaveTypeMapper leaveTypeMapper) {
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveTypeMapper = leaveTypeMapper;
    }

    @Override
    public LeaveTypeResponseDto addLeaveType(LeaveTypeRequestDto dto) {
        LeaveType leaveType = leaveTypeMapper.toEntity(dto);
        LeaveType savedLeaveType = leaveTypeRepository.save(leaveType);
        return leaveTypeMapper.toResponse(savedLeaveType);
    }

    @Override
    public List<LeaveTypeResponseDto> getAllLeaveTypes() {
        return leaveTypeRepository.findAll().stream()
                .map(leaveTypeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
