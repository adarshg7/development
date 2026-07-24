package com.employee.leaveManagement.service;

import com.employee.leaveManagement.dto.leaveType.LeaveTypeRequestDto;
import com.employee.leaveManagement.dto.leaveType.LeaveTypeResponseDto;

import java.util.List;

public interface LeaveTypeService {
    LeaveTypeResponseDto addLeaveType(LeaveTypeRequestDto dto);
    List<LeaveTypeResponseDto> getAllLeaveTypes();
}
