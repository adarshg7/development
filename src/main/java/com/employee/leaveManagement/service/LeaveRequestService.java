package com.employee.leaveManagement.service;

import com.employee.leaveManagement.dto.leave.LeaveRequestDto;
import com.employee.leaveManagement.dto.leave.LeaveResponseDto;

import java.util.List;

public interface LeaveRequestService {
    LeaveResponseDto createLeaveRequest(LeaveRequestDto dto);
    List<LeaveResponseDto> getAllLeaveRequests();
}
