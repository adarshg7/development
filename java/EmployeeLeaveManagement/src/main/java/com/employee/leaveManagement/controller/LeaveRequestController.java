package com.employee.leaveManagement.controller;

import com.employee.leaveManagement.dto.leave.LeaveRequestDto;
import com.employee.leaveManagement.dto.leave.LeaveResponseDto;
import com.employee.leaveManagement.service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveResponseDto> createLeaveRequest(@RequestBody LeaveRequestDto dto) {
        return new ResponseEntity<>(leaveRequestService.createLeaveRequest(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponseDto>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }
}
