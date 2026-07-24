package com.employee.leaveManagement.mapper;

import com.employee.leaveManagement.dto.leave.LeaveRequestDto;
import com.employee.leaveManagement.dto.leave.LeaveResponseDto;
import com.employee.leaveManagement.entity.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestMapper {

    public LeaveRequest toEntity(LeaveRequestDto dto){

        LeaveRequest leave = new LeaveRequest();

        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());

        return leave;
    }

    public LeaveResponseDto toResponse(LeaveRequest leave){

        LeaveResponseDto dto = new LeaveResponseDto();

        dto.setId(leave.getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());

        dto.setEmployeeName(
                leave.getEmployee().getFirstName()
        );

        dto.setLeaveType(
                leave.getLeaveType().getLeaveName()
        );

        return dto;
    }

}