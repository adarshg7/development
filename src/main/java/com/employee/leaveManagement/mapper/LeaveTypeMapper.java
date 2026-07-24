package com.employee.leaveManagement.mapper;

import com.employee.leaveManagement.dto.leaveType.LeaveTypeRequestDto;
import com.employee.leaveManagement.dto.leaveType.LeaveTypeResponseDto;
import com.employee.leaveManagement.entity.LeaveType;
import org.springframework.stereotype.Component;

@Component
public class LeaveTypeMapper {

    public LeaveType toEntity(LeaveTypeRequestDto dto) {
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveName(dto.getLeaveName());
        leaveType.setMaximumLeaves(dto.getMaximumLeaves());
        return leaveType;
    }

    public LeaveTypeResponseDto toResponse(LeaveType leaveType) {
        LeaveTypeResponseDto dto = new LeaveTypeResponseDto();
        dto.setId(leaveType.getId());
        dto.setLeaveName(leaveType.getLeaveName());
        dto.setMaximumLeaves(leaveType.getMaximumLeaves());
        return dto;
    }
}
