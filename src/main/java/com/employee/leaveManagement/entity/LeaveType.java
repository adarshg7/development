package com.employee.leaveManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String leaveName;
    private Integer maximumLeaves;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRequest> leaveRequests;

}
