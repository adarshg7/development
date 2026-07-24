package com.employee.leaveManagement.service.impl;

import com.employee.leaveManagement.dto.leave.LeaveRequestDto;
import com.employee.leaveManagement.dto.leave.LeaveResponseDto;
import com.employee.leaveManagement.entity.Employee;
import com.employee.leaveManagement.entity.LeaveRequest;
import com.employee.leaveManagement.entity.LeaveType;
import com.employee.leaveManagement.mapper.LeaveRequestMapper;
import com.employee.leaveManagement.repository.EmployeeRepository;
import com.employee.leaveManagement.repository.LeaveRequestRepository;
import com.employee.leaveManagement.repository.LeaveTypeRepository;
import com.employee.leaveManagement.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveRequestMapper leaveRequestMapper;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository,
                                   EmployeeRepository employeeRepository,
                                   LeaveTypeRepository leaveTypeRepository,
                                   LeaveRequestMapper leaveRequestMapper) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveRequestMapper = leaveRequestMapper;
    }

    @Override
    public LeaveResponseDto createLeaveRequest(LeaveRequestDto dto) {
        LeaveRequest leaveRequest = leaveRequestMapper.toEntity(dto);
        
        // Fetch the associated Employee and LeaveType
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        
        LeaveType leaveType = leaveTypeRepository.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("Leave Type Not Found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        
        // Give new leave requests a default PENDING status
        leaveRequest.setStatus("PENDING"); 

        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);
        return leaveRequestMapper.toResponse(savedLeaveRequest);
    }

    @Override
    public List<LeaveResponseDto> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
                .map(leaveRequestMapper::toResponse)
                .collect(Collectors.toList());
    }
}
