package com.employee.leaveManagement.controller;

import com.employee.leaveManagement.dto.leaveType.LeaveTypeRequestDto;
import com.employee.leaveManagement.dto.leaveType.LeaveTypeResponseDto;
import com.employee.leaveManagement.service.LeaveTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-types")
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @PostMapping
    public ResponseEntity<LeaveTypeResponseDto> addLeaveType(@RequestBody LeaveTypeRequestDto dto) {
        return new ResponseEntity<>(leaveTypeService.addLeaveType(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaveTypeResponseDto>> getAllLeaveTypes() {
        return ResponseEntity.ok(leaveTypeService.getAllLeaveTypes());
    }
}
